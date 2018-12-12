package com.citytechinc.cq.component.gradle
import org.apache.maven.plugin.logging.Log
import org.slf4j.Logger
class GradleMavenLog implements Log{
	Logger log

	GradleMavenLog(Logger log){
		this.log=log
	}

	public void debug(CharSequence charSequence) {
		this.log.debug(charSequence)
	}

	public void debug(Throwable throwable) {
		this.log.debug(throwable)
	}

	public void debug(CharSequence charSequence, Throwable throwable) {
		this.log.debug(charSequence, throwable)
	}

	public void error(CharSequence charSequence) {
		this.log.error(charSequence)
	}

	public void error(Throwable throwable) {
		this.log.error(throwable)
	}

	public void error(CharSequence charSequence, Throwable throwable) {
		this.log.error(charSequence, throwable)
	}

	public void info(CharSequence charSequence) {
		this.log.info(charSequence)
	}

	public void info(Throwable throwable) {
		this.log.info(throwable)
	}

	public void info(CharSequence charSequence, Throwable throwable) {
		this.log.info(charSequence, throwable)
	}

	public boolean isDebugEnabled() {
		return this.log.debugEnabled
	}

	public boolean isErrorEnabled() {
		return this.log.errorEnabled
	}

	public boolean isInfoEnabled() {
		return this.log.infoEnabled
	}

	public boolean isWarnEnabled() {
		return this.log.warnEnabled
	}

	public void warn(CharSequence charSequence) {
		this.log.warn(charSequence)
	}

	public void warn(Throwable throwable) {
		this.log.warn(throwable)
	}

	public void warn(CharSequence charSequence, Throwable throwable) {
		this.log.warn(charSequence, throwable)
	}
}
