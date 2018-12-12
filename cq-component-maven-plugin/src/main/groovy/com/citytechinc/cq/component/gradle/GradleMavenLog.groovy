package com.citytechinc.cq.component.gradle

import org.apache.maven.plugin.logging.Log
import org.slf4j.Logger

class GradleMavenLog implements Log {
    Logger log

    GradleMavenLog(Logger log) {
        this.log = log
    }

    void debug(CharSequence charSequence) {
        this.log.debug(charSequence)
    }

    void debug(Throwable throwable) {
        this.log.debug(throwable)
    }

    void debug(CharSequence charSequence, Throwable throwable) {
        this.log.debug(charSequence, throwable)
    }

    void error(CharSequence charSequence) {
        this.log.error(charSequence)
    }

    void error(Throwable throwable) {
        this.log.error(throwable)
    }

    void error(CharSequence charSequence, Throwable throwable) {
        this.log.error(charSequence, throwable)
    }

    void info(CharSequence charSequence) {
        this.log.info(charSequence)
    }

    void info(Throwable throwable) {
        this.log.info(throwable)
    }

    void info(CharSequence charSequence, Throwable throwable) {
        this.log.info(charSequence, throwable)
    }

    boolean isDebugEnabled() {
        return this.log.debugEnabled
    }

    boolean isErrorEnabled() {
        return this.log.errorEnabled
    }

    boolean isInfoEnabled() {
        return this.log.infoEnabled
    }

    boolean isWarnEnabled() {
        return this.log.warnEnabled
    }

    void warn(CharSequence charSequence) {
        this.log.warn(charSequence)
    }

    void warn(Throwable throwable) {
        this.log.warn(throwable)
    }

    void warn(CharSequence charSequence, Throwable throwable) {
        this.log.warn(charSequence, throwable)
    }
}
