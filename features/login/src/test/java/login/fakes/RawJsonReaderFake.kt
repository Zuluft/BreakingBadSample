package login.fakes

import com.zuluft.mocks.reader.RawJsonReader
import login.data.LoginData

class RawJsonReaderFake : RawJsonReader {
    override fun readRawRes(resId: Int): String {
        return LoginData.USERS
    }
}