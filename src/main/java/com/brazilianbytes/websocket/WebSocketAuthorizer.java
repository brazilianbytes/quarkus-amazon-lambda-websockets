package com.brazilianbytes.websocket;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayCustomAuthorizerEvent;
import com.amazonaws.services.lambda.runtime.events.IamPolicyResponse;
import com.amazonaws.services.lambda.runtime.events.IamPolicyResponseV1;
import com.google.gson.Gson;
import jakarta.inject.Named;

import java.util.Collections;
import java.util.Objects;

import static java.lang.System.Logger;
import static java.lang.System.Logger.Level;

/**
 * The WebSocketAuthorizer class handles the API Gateway custom authorizer event and returns an IAM policy response.
 */
@Named("Authorizer")
public class WebSocketAuthorizer implements RequestHandler<APIGatewayCustomAuthorizerEvent, IamPolicyResponse> {

  private final Gson gson = new Gson();
  private final Logger logger = System.getLogger(this.getClass().getName());

  /**
   * Handles the API Gateway custom authorizer event and returns an IAM policy response.
   *
   * @param event   The custom authorizer event.
   * @param context The Lambda function execution context.
   * @return The IAM policy response.
   */
  @Override
  public IamPolicyResponse handleRequest(final APIGatewayCustomAuthorizerEvent event, final Context context) {
    this.logger.log(Level.ALL, "WebSocketAuthorizer.handleRequest");

    final String principalId = event.getHeaders().get("Sec-WebSocket-Protocol");
    final String resource = String.format("arn:aws:execute-api:%s:*:%s/%s/*",
        System.getenv("AWS_REGION"),
        event.getRequestContext().getApiId(),
        event.getRequestContext().getStage()
    );

    IamPolicyResponse response = IamPolicyResponse
        .builder()
        .withPrincipalId(principalId)
        .withPolicyDocument(
            IamPolicyResponse.PolicyDocument.builder()
                .withVersion(IamPolicyResponseV1.VERSION_2012_10_17)
                .withStatement(
                    Collections.singletonList(
                        Objects.equals(principalId, "ALLOW") ?
                            IamPolicyResponse.allowStatement(resource) :
                            IamPolicyResponse.denyStatement(resource)
                    )
                )
                .build()
        )
        .build();

    this.logger.log(Level.ALL, gson.toJson(event));
    this.logger.log(Level.ALL, gson.toJson(context));
    this.logger.log(Level.ALL, gson.toJson(response));

    return response;
  }
}
