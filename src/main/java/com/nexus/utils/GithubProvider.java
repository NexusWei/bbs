package com.nexus.utils;

import com.alibaba.fastjson.JSON;
import com.nexus.pojo.AccessToken;
import com.nexus.pojo.GithubParams;
import com.nexus.pojo.GithubUser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:用于获取access_token和user信息
 */

@Component
public class GithubProvider {

    @Autowired
    private GithubParams githubParams;

    /**
     * @description:获取AccessToken
     */
    public String getAccessToken(AccessToken accessToken) {
        // 这里整段从 okhttp官方复制，然后修改一下。
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        // 将accessTokenDto转为json字符串传入参数
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessToken));
        Request request = new Request.Builder()
                // url 的地址，参考github文档获取token的url地址。
                // https://github.com/login/oauth/access_token
                .url(githubParams.getToken_uri())
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();

            // 得到的是类似这样的字符串，我们需要将它分割，只要access_token部分
            // access_token=9566ba3483a556c610be42d44338f3fd16a3b8d1&scope=&token_type=bearer

            // 先按照&切割，只要&第0个。
            // String str = string.split("&")[0];
            // 在按照=切割，只要=第1个。
            // String s = str.split("=")[1];
            // return s;

            // 将切割合并为一行。
            return string.split("&")[0].split("=")[1];
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据access_token获取用户信息
     */
    public GithubUser getGithubUser(String access_token) {
        // 这里还是从okhttp官网复制，然后修改。
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                // https://api.github.com/user
                .url(githubParams.getUser_uri())
                .header("Authorization","token "+access_token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            // 得到的是json字符串，因此需要转为GithubUser对象
            return JSON.parseObject(response.body().string(), GithubUser.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
