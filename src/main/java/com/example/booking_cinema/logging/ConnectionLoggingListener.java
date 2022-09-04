package com.example.booking_cinema.logging;

import lombok.RequiredArgsConstructor;
import net.ttddyy.dsproxy.listener.logging.AbstractQueryLoggingListener;
import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import org.apache.catalina.core.ApplicationContext;

import java.util.logging.Logger;

import static java.util.logging.Level.INFO;


public class ConnectionLoggingListener extends AbstractQueryLoggingListener {

    private final static Logger LOG = Logger.getLogger(ConnectionLoggingListener.class.getCanonicalName());
    private final DefaultQueryLogEntryCreator creator;

    public ConnectionLoggingListener(DefaultQueryLogEntryCreator creator) {
        this.creator = creator;
        this.loggingCondition = () -> true;
        creator.setMultiline(true);
        setQueryLogEntryCreator(creator);
    }

    @Override
    protected void writeLog(String s) {
        LOG.log(INFO, s);
    }
}
