package com.cloud.discoveryclient2.api.v1;

import org.springframework.web.bind.annotation.ResponseBody;

public interface CommServiceApi {

	public @ResponseBody String getFullWelcomeText();
}
