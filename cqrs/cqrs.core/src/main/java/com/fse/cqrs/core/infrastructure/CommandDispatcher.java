package com.fse.cqrs.core.infrastructure;

import com.fse.cqrs.core.commands.BaseCommand;
import com.fse.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {

    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

    void send(BaseCommand command);
}
