package com.dotnar.usc.web.oltu;
//
//import org.apache.oltu.oauth2.client.OAuthClient;
//import org.apache.oltu.oauth2.client.URLConnectionClient;
//import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
//import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
//import org.apache.oltu.oauth2.common.OAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/server")
@Controller
public class ServerController {


    String clientId = null;

    String clientSecret = null;

    String accessTokenUrl = null;

    String userInfoUrl = null;

    String redirectUrl = null;

    String response_type = null;

    String code = null;

    //提交申请code的请求

//    @RequestMapping("/getToken")
//    @ResponseBody
//    public Object getToken() {
//
//        clientId = "oauth_client";
//        clientSecret = "oauth_client_secret=oauth_client_secret";
//        accessTokenUrl = "http://localhost:3333/oauth/authorize";
//        redirectUrl = "http://l192.168.2.111:3333/test";
//        response_type = "token";
////        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//        String requestUrl = null;
//        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//        try {
//            //构建oauthd的请求。设置请求服务地址（accessTokenUrl）、clientId、response_type、redirectUrl
//            OAuthClientRequest accessTokenRequest = OAuthClientRequest
//                    .authorizationLocation(accessTokenUrl)
//                    .setResponseType(response_type)
//                    .setClientId(clientId)
//                    .setRedirectURI(redirectUrl)
//                    .buildQueryMessage();
////            accessTokenRequest= OAuthClientRequest.AuthenticationRequestBuilder.
////            requestUrl = accessTokenRequest.getLocationUri();
//            System.out.println(requestUrl);
//            //去服务端请求access token，并返回响应
//            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
//            //获取服务端返回过来的access token
//            String accessToken = oAuthResponse.getAccessToken();
//            //查看access token是否过期
//            Long expiresIn = oAuthResponse.getExpiresIn();
//            System.out.println("客户端/callbackCode方法的token：：：" + accessToken);
//            System.out.println("-----------客户端/callbackCode--------------------------------------------------------------------------------");
//            return "redirect:http://localhost:8081/oauthclient01/server/accessToken?accessToken=" + accessToken;
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:http://localhost:8082/oauthserver/" + requestUrl;
//    }
}