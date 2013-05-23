package com.citytechinc.cq.component.dialog.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.exception.OutputFailureException;
import com.citytechinc.cq.component.dialog.factory.DialogFactory;
import com.citytechinc.cq.component.dialog.impl.Dialog;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.dialog.xml.DialogXmlWriter;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class DialogUtil {
	private DialogUtil(){};

	/**
	 * Writes a dialog.xml file, the path of which being based on the component
	 * Class.
	 * 
	 * @param dialog
	 * @param componentClass
	 * @return The written file
	 * @throws OutputFailureException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static File writeDialogToFile(ComponentNameTransformer transformer, Dialog dialog, CtClass componentClass, File buildDirectory,
		String componentPathBase, String defaultComponentPathSuffix) throws OutputFailureException, IOException,
		ParserConfigurationException, TransformerException, ClassNotFoundException, IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		File componentOutputDirectory = ComponentMojoUtil.getOutputDirectoryForComponentClass(transformer, componentClass, buildDirectory,
			componentPathBase, defaultComponentPathSuffix);
	
		File dialogFile = new File(componentOutputDirectory, dialog.getFileName());
	
		if (dialogFile.exists()) {
			dialogFile.delete();
		}
	
		dialogFile.createNewFile();
	
		DialogXmlWriter.writeDialog(dialog, new FileOutputStream(dialogFile));
	
		return dialogFile;
	}

	/**
	 * Writes a provided dialog file to a provided archive output stream at a
	 * path determined by the class of the component.
	 * 
	 * @param dialogFile
	 * @param componentClass
	 * @param archiveStream
	 * @param reservedNames A list of files which already exist within the Zip
	 *            Archive. If a dialog.xml file already exists for a particular
	 *            component, it is left untouched.
	 * @param componentPathBase
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void writeDialogToArchiveFile(ComponentNameTransformer transformer, File dialogFile, CtClass componentClass,
		ZipArchiveOutputStream archiveStream, Set<String> reservedNames, String componentPathBase,
		String defaultComponentPathSuffix) throws IOException, ClassNotFoundException {
		String dialogFilePath = componentPathBase + "/"
			+ ComponentMojoUtil.getComponentPathSuffixForComponentClass(componentClass, defaultComponentPathSuffix) + "/"
			+ ComponentMojoUtil.getComponentNameForComponentClass(transformer, componentClass) + "/" + dialogFile.getName();
	
		ComponentMojoUtil.getLog().debug("Archiving dialog file " + dialogFilePath);
	
		// TODO: I'd like to move this check somewhere before we go through the
		// trouble of creating the dialog object itself
		if (!reservedNames.contains(dialogFilePath.toLowerCase())) {
	
			ZipArchiveEntry entry = new ZipArchiveEntry(dialogFile, dialogFilePath);
	
			archiveStream.putArchiveEntry(entry);
	
			IOUtils.copy(new FileInputStream(dialogFile), archiveStream);
	
			archiveStream.closeArchiveEntry();
	
		} else {
			ComponentMojoUtil.getLog().debug("Existing file found at " + dialogFilePath);
		}
	}

	/**
	 * Constructs a list of Dialog objects based on Classes annotated by
	 * Component annotations. Scans the provided list of classes constructing a
	 * Dialog object for each one annotated with the Component annotation. Any
	 * classes provided in the class list which are not thusly annotated are
	 * ignored.
	 * 
	 * @param classList
	 * @param zipOutputStream
	 * @param reservedNames
	 * @param xtypeMap
	 * @param classLoader
	 * @param classPool
	 * @return A list of constructed Dialog objects
	 * @throws InvalidComponentClassException
	 * @throws InvalidComponentFieldException
	 * @throws OutputFailureException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws ClassNotFoundException
	 * @throws CannotCompileException
	 * @throws NotFoundException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static List<Dialog> buildDialogsFromClassList(ComponentNameTransformer transformer, List<CtClass> classList,
		ZipArchiveOutputStream zipOutputStream, Set<String> reservedNames, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> widgetMakerMap, ClassLoader classLoader, ClassPool classPool, File buildDirectory,
		String componentPathBase, String defaultComponentPathSuffix) throws InvalidComponentClassException,
		InvalidComponentFieldException, OutputFailureException, IOException, ParserConfigurationException,
		TransformerException, ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException,
		NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
		NoSuchMethodException {
	
		final List<Dialog> dialogList = new ArrayList<Dialog>();
	
		for (CtClass curClass : classList) {
			ComponentMojoUtil.getLog().debug("Checking class for Component annotation " + curClass);
	
			Component annotation = (Component) curClass.getAnnotation(Component.class);
	
			ComponentMojoUtil.getLog().debug("Annotation : " + annotation);
	
			if (annotation != null) {
				boolean hasDialogField = false;
				for (CtField curField : ComponentMojoUtil.collectFields(curClass)) {
					if (curField.hasAnnotation(DialogField.class)) {
						hasDialogField = true;
						break;
					}
				}
				if(!hasDialogField){
					for (CtMethod curMethod : ComponentMojoUtil.collectMethods(curClass)) {
						if (curMethod.hasAnnotation(DialogField.class)) {
							hasDialogField = true;
							break;
						}
					}
				}
				if (hasDialogField) {
					ComponentMojoUtil.getLog().debug("Processing Component Class " + curClass);
					Dialog builtDialog = DialogFactory.make(curClass, xtypeMap, widgetMakerMap, classLoader, classPool);
					dialogList.add(builtDialog);
					File dialogFile = writeDialogToFile(transformer, builtDialog, curClass, buildDirectory, componentPathBase,
						defaultComponentPathSuffix);
					writeDialogToArchiveFile(transformer, dialogFile, curClass, zipOutputStream, reservedNames, componentPathBase,
						defaultComponentPathSuffix);
				}
			}
		}
	
		return dialogList;
	
	}

}
