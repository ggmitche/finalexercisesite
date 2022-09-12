package com.aca.fitnessapp.controller;

import java.util.List;

import com.aca.fitnessapp.model.ClientStack;
import com.aca.fitnessapp.service.StackService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/stacks")
public class StackController {

	private StackService service = new StackService();
	
	@GET
	@Path("/client")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientStack> getExercises() {
		return service.getClientStacks();
	}
}
