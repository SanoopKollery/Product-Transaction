package com.fse.product.cmd;

import com.fse.cqrs.core.infrastructure.CommandDispatcher;
import com.fse.product.cmd.api.commands.Transaction;
import com.fse.product.cmd.api.commands.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan("com.fse.cqrs.core.events")
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;



	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {

		commandDispatcher.registerHandler(Transaction.class, commandHandler::handle);
	}

}
