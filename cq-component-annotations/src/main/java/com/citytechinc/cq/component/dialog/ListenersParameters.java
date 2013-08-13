/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.annotations.Listener;

public class ListenersParameters extends DialogElementParameters {
	public static final String FIELD_NAME = "listeners";
	public static final String PRIMARY_TYPE = "nt:unstructured";
	private Listener[] listenerAnnotations;

	public Listener[] getListenerAnnotations() {
		return listenerAnnotations;
	}

	public void setListenerAnnotations(Listener[] listenerAnnotations) {
		this.listenerAnnotations = listenerAnnotations;
	}

	@Override
	public String getPrimaryType() {
		return PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for ListenerParameters");
	}

	@Override
	public String getFieldName() {
		return FIELD_NAME;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("FieldName is Static for ListenerParameters");
	}
}
