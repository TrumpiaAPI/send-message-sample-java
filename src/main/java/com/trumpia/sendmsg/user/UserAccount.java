package com.trumpia.sendmsg.user;

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


/*
 * UserAccount data object
 * */
public class UserAccount {

    String userId;
    String apikey;

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getApikey() {

        return apikey;
    }

    public void setApikey(String apikey) {

        this.apikey = apikey;
    }
}
