package com.amazonaws.lambda.course;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.lambda.course.CourseFunctionHandler;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

    private static CourseRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        CourseFunctionHandler handler = new CourseFunctionHandler();
        Context ctx = createContext();

        //CourseResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertNotNull(new Object());
    }
}
