# jetter

Easy Jersey2-Jetty-Swagger backed API framework

## What is Jetter?

Do you find yourself writing a system which has a few APIs that you spend a lot of time packaging and starting up?

Do you write a lot of plumbing code in your system that you have to keep writing for every project that you create?

Jetter brings together Jersey 2, Jetty and Swagger so you can create a project with a few APIs and run it as a self-contained JAR very easily, while providing documentation and micro-servicey goodness.

## So how do I get it?

You can add `jetter` as a Maven dependency:

	<dependency>
	    <groupId>com.caffinc.jetter</groupId>
	    <artifactId>jetter-core</artifactId>
	    <version>0.3.0</version>
	</dependency>

## And how do I use it?

You need your Jersey 2 resource classes with the APIs you want. The following class is an example extract from one of the other projects we have called `Grex` (You should check it out):

	package com.caffinc.grex.app.resources;
	
	import com.caffinc.grex.common.entities.NodeStatus;
	import com.caffinc.grex.core.Load;
	import io.swagger.annotations.Api;
	import io.swagger.annotations.ApiOperation;
	
	import javax.ws.rs.*;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.Response;
	
	/**
	 * Grex Node API
	 *
	 * @author Sriram
	 */
	@Path("/api/node")
	@Api(value = "Node", description = "Controls the Node")
	public class NodeResource {
	    @GET
	    @Path("/status")
	    @ApiOperation(value = "Status", notes = "Returns the status of this node", response = NodeStatus.class)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getStatus(@HeaderParam("authorization") final String authorizationHeader) {
	        return Response.ok(Load.getInstance().getStatus()).build();
	    }
		...
	}

It is annotated with Jersey 2 and Swagger annotations, which become available when you include `jetter` in your dependencies. This takes care of the API, and documentation for the API.

Now you need to build this into a project which puts this API into a neatly packaged JAR which can be started using a command like `java -jar myjar.jar`. Here is where the core `jetter` code and Jetty comes into play.

In your `main()` method in a class called `App`, include the following code:

	Api api = new Api(SERVICE_PORT)
                    .setBaseUrl(BASE_URL)
                    .addServiceResource(NodeResource.class, "Grex API", "API for managing Grex");

	api.start();

Now you have an API system! Let's say your `BASE_URL` is `/grex` and your `SERVICE_PORT` is `2345`. When you start your JAR from the command line, your API would be available at `http://localhost:2345/grex`, which can be redirected to from a server like `nginx`. Your Swagger API documentation would be available at the `http://localhost:2345/grex/swagger.json` endpoint.

There are a bunch of methods available in the `Api` class, which follows the builder pattern. Suppose you have a bunch of static resources. You can add them too:

	api.addStaticResource(
                    App.class.getClassLoader().getResource("ui").toURI().toString(),
                    BASE_URL);

The `start()` method is blocking. You can also call the non-blocking `startNonBlocking()` method which returns the `Jetty Server` used by the `Api` as well:

    Server server = api.startNonBlocking();

You can enable CORS:

	api.enableCors();

You can add a filter:

	api.addFilter(AuthorizationFilter.class);

And finally, you can build a fat jar to package it all as a neat little (fat) JAR file:


    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>2.3</version>
                <configuration>
                    <transformers>
                        <transformer
                                implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                            <mainClass>com.caffinc.grex.app.App</mainClass>
                        </transformer>
                    </transformers>
                </configuration>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

## Is this thing stable?

The current build status is:

![Jetter Build Status](https://travis-ci.org/caffinc/jetter.svg?branch=master)

If this thing is red, we probably broke something. Don't judge us :(

## Something's broken though...

Maybe we can help. Contact admin@caffinc.com and *maybe* we know how to fix what you broke.