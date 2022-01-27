package login.data

import com.zuluft.login.domain.models.LoginDataModel

object LoginData {
    val USERS = "[\n" +
            "  {\n" +
            "    \"id\": 123,\n" +
            "    \"username\": \"user1\",\n" +
            "    \"password\": \"123456\",\n" +
            "    \"fullName\": \"Mehdi Benatia\",\n" +
            "    \"email\": \"aaa@bb.cc\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 124,\n" +
            "    \"username\": \"user2\",\n" +
            "    \"password\": \"123456\",\n" +
            "    \"fullName\": \"Mehdi Benatia\",\n" +
            "    \"email\": \"aaa@bb.cc\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 125,\n" +
            "    \"username\": \"user4\",\n" +
            "    \"password\": \"123456\",\n" +
            "    \"fullName\": \"Mehdi Benatia\",\n" +
            "    \"email\": \"aaa@bb.cc\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 126,\n" +
            "    \"username\": \"user3\",\n" +
            "    \"password\": \"123456\",\n" +
            "    \"fullName\": \"Mehdi Benatia\",\n" +
            "    \"email\": \"aaa@bb.cc\"\n" +
            "  }\n" +
            "]"

    val CORRECT_USER = LoginDataModel("user3", "123456")
    val INCORRECT_USER = LoginDataModel("user56", "34569999")
}