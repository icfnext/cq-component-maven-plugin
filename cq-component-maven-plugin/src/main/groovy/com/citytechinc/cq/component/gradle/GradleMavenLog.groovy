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
