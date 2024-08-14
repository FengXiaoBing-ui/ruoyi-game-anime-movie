<template>
	<view>
		<up-sticky offset-top="0" bgColor="white" style="padding: 10rpx 24rpx;">
			<up-search @change="searChange" @search="search" shape="square" placeholder="请输入游戏名(英文名搜索更精确)" v-model="keyWord"></up-search>
		</up-sticky>
		<div class="content">
			<view class="list" v-for="item in list" :key="item.id" @click="goDetails(item.id)">
				<image class="cover" :src="item.coverImg" mode="aspectFill"></image>
				<view class="padding-xs flex flex-direction">
					<text class="text-bold text-cut text-lg">{{ item.zhTitle }}</text>
					<text class="">{{ item.title }}</text>
					<text class="text-sm text-gray light">下载量：{{ item.downLoadCount }}</text>
					<text class="text-sm text-gray light">发布时间：{{ item.releaseDate }}</text>
					<text class="text-sm text-gray light">大小：{{ item.gameSize }}</text>
				</view>
			</view>
		</div>
	</view>
	
</template>

<script>
	import config from "@/config/config.js"
	export default {
		data() {
			return {
				list:[],
				total:1,
				pageSize:6,
				pageNum:1,
				keyWord:"",
				status:"loadmore"
			}
		},
		onLoad() {
			this.getGameList()
		},
		onReachBottom(){
			if(this.pageNum * this.pageSize<this.total){
				this.pageNum ++;
				this.getGameList()
			}
		},
		methods: {
			searChange(){
				if(this.keyWord==""){
					this.init()
					this.getGameList()
				}
			},
			search(){
				this.init()
				this.getGameList()
			},
			init(){
				this.pageNum = 1
				this.pageSize = 6
				this.list = []
			},
			async getGameList(){
				this.status = 'loading';
				const res = await this.$http.getList({pageNum:this.pageNum,pageSize:this.pageSize,title:this.keyWord,})
				this.list = [...this.list,...res.rows]
				this.total = res.total
				this.status = 'loadmore';
				if(this.pageNum * this.pageSize>=this.total) {
					this.status = 'nomore';
				}
			},
			goDetails(id){
				uni.navigateTo({
					url:"/pages/gameDetails/gameDetails?id="+id
				})
			}
		}
	}
</script>

<style scoped>
.content{
	padding: 12px;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	flex-wrap: wrap;
	padding-bottom: 50px;
}
.list{
	box-shadow: 4px 4px 10px #eaeaea;
	border-radius: 4px;
	width: 170px;
	margin-bottom: 20px;
}
.cover{
	width: 170px;
	height: 230px;
	border-radius: 4px 4px 0 0;
}
</style>
