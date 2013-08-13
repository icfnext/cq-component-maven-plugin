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
package com.citytechinc.cq.component.maven.util;

import org.apache.maven.plugin.logging.Log;

public class LogSingleton {

	private static LogSingleton instance;

	private Log logger;

	private LogSingleton() {
	}

	public static final LogSingleton getInstance() {
		if (instance == null) {
			instance = new LogSingleton();
		}

		return instance;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public void debug(CharSequence content) {
		if (logger != null) {
			logger.debug(content);
		}
	}

	public void debug(CharSequence content, Throwable error) {
		if (logger != null) {
			logger.debug(content, error);
		}
	}

	public void debug(Throwable error) {
		if (logger != null) {
			logger.debug(error);
		}
	}

	public void error(CharSequence content) {
		if (logger != null) {
			logger.error(content);
		}
	}

	public void error(CharSequence content, Throwable error) {
		if (logger != null) {
			logger.error(content, error);
		}
	}

	public void error(Throwable error) {
		if (logger != null) {
			logger.error(error);
		}
	}

	public void info(CharSequence content) {
		if (logger != null) {
			logger.info(content);
		}
	}

	public void info(CharSequence content, Throwable error) {
		if (logger != null) {
			logger.info(content, error);
		}
	}

	public void info(Throwable error) {
		if (logger != null) {
			logger.info(error);
		}
	}
}
