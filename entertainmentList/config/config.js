
const AESKey = ""
let BASE_URL, UNI_CLOUD_FUN, VIDEO_URL, IMG_SERVER, UPLOAD_IMG_SERVER,MINIPROGRAM = "";
if (process.env.NODE_ENV === "development") {
	//开发环境下编译
	console.log("开发环境");
	BASE_URL = "http://127.0.0.1:8080";
	UNI_CLOUD_FUN = "";
	VIDEO_URL = "https://danmu.yhdmjx.com/m3u8.php?url=";
	IMG_SERVER = "";
	UPLOAD_IMG_SERVER = "";
	MINIPROGRAM = 2;
}

if (process.env.NODE_ENV === "production") {
	// 生产环境下编译
	console.log("生产环境");
	BASE_URL = "http://127.0.0.1:8080";
	UNI_CLOUD_FUN = "";
	VIDEO_URL = "https://danmu.yhdmjx.com/m3u8.php?url=";
	IMG_SERVER = "";
	UPLOAD_IMG_SERVER = "";
	MINIPROGRAM = 0;
}

if (process.env.NODE_ENV === "test") {
	// 测试环境下编译
	console.log("测试环境");
	BASE_URL = "http://127.0.0.1:8080";
	UNI_CLOUD_FUN = "";
	VIDEO_URL = "https://danmu.yhdmjx.com/m3u8.php?url=";
	IMG_SERVER = "";
	UPLOAD_IMG_SERVER = "";
	MINIPROGRAM = 0;
}

export default {
	AESKey,
	BASE_URL,
	UNI_CLOUD_FUN,
	VIDEO_URL,
	IMG_SERVER,
	UPLOAD_IMG_SERVER,
	MINIPROGRAM
};