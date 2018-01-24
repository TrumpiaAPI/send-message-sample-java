package com.trumpia.sendmsg;

import com.trumpia.sendmsg.command.ResultResolver;
import com.trumpia.sendmsg.command.TrumpiaAPIcaller;
import com.trumpia.sendmsg.user.UserAccount;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*This file is part of Sign-up Page Sample.

The Sign-up Page Sample is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

The Sign-up Page Sample is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with The Sign-up Page Sample.  If not, see <http://www.gnu.org/licenses/>. 
*/


@Controller
public class RequestController implements BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
    private UserAccount user;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        return "index";
    }

    /*
     * Get form data(mobileNumber, message) through /sendmsg page.
     * and alert if that function is whether success or fail
     **/
    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public String test(HttpServletRequest request, Model model) throws IOException, InterruptedException {

        String mobile = request.getParameter("mobileNumber");
        String msg = request.getParameter("message");
        String resultMessage;
        JSONObject response = TrumpiaAPIcaller.send(mobile, msg, user);

        //MPCE4001 status code means it is on progress.
        if (response != null && response.has("status_code") && response.getString("status_code").equals("MPCE4001")) {
            ResultResolver resolver = new ResultResolver(response.getString("request_id"), user);
            resultMessage = resolver.getResultMessage();
        } else {
            resultMessage = "Fail";
        }

        model.addAttribute("msg", resultMessage);
        model.addAttribute("url", "/sendmsg");

        return "redirect";
    }

    /*
     * Get  your bean that you made through user.properties, applicaitonContext.xml
     * */
    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {

        GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        user = context.getBean("userAccount", com.trumpia.sendmsg.user.UserAccount.class);
    }

}
