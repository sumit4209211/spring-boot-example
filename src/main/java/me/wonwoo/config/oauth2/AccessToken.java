package me.wonwoo.config.oauth2;

/**
 * Created by wonwoo on 15. 12. 20..
 */
public class AccessToken {
    private String access_token;
    private String token_type;
    private String bearer;
    private String refresh_token;
    private Long expires_in;
    private String scope;

    public AccessToken(){
    }

    public AccessToken(String access_token, String bearer, Long expires_in, String refresh_token, String scope, String token_type) {
        this.access_token = access_token;
        this.bearer = bearer;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.scope = scope;
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AccessToken{");
        sb.append("access_token='").append(access_token).append('\'');
        sb.append(", token_type='").append(token_type).append('\'');
        sb.append(", bearer='").append(bearer).append('\'');
        sb.append(", refresh_token='").append(refresh_token).append('\'');
        sb.append(", expires_in=").append(expires_in);
        sb.append(", scope='").append(scope).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
