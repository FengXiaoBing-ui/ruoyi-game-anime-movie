import req from "./request.js"

export default {
	getList(data){
		return req.ajaxRequest({url:"/module/dataGame/list",data})
	},
	getListDeatils(data){
		return req.ajaxRequest({url:`/module/dataGame/${data}`})
	},
	getListChildDeatils(data){
		return req.ajaxRequest({url:`/module/dataGame/dataChild/${data}`})
	},
	getMoveList(data){
		return req.ajaxRequest({url:"/module/anime/list",data})
	},
	getMoveListDeatils(data){
		return req.ajaxRequest({url:`/module/anime/${data}`})
	},
	getChAnimeList(data){
		return req.ajaxRequest({url:"/module/chAnime/list",data})
	},
	getChAnimeListDeatils(data){
		return req.ajaxRequest({url:`/module/chAnime/${data}`})
	},
	getAnimeMoiveList(data){
		return req.ajaxRequest({url:"/module/movie/list",data})
	},
	getAnimeMoiveListDeatils(data){
		return req.ajaxRequest({url:`/module/movie/${data}`})
	},
	getVideoUrl(data){
		return req.ajaxRequest({url:`/module/AnimeChListController/getVideo`,data})
	},
	getMovieVideoUrl(data){
		return req.ajaxRequest({url:`/module/DataMovieListController/getVideo`,data})
	},
	getMoiveList(data){
		return req.ajaxRequest({url:"/module/data_movie/list",data})
	},
	getMoiveListDeatils(data){
		return req.ajaxRequest({url:`/module/data_movie/${data}`})
	},
}