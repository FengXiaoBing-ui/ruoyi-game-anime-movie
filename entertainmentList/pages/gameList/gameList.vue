<template>
	<view>
		<up-sticky offset-top="0" bgColor="white" style="padding: 10rpx 24rpx;">
			<up-search @change="searChange" @search="search" shape="square" placeholder="请输入游戏名(英文名搜索更精确)" v-model="keyWord"></up-search>
		</up-sticky>
		<view class="screen">
			<view class="screen-list">
				<view @click="activeType(item,'nowAnimeType')" class="tag-y" :class="item == nowAnimeType?'avtiveY':''"
					v-for="item in animetype" :key="item">{{ item }}</view>
			</view>
			<view class="screen-list">
				<view @click="activeType(item,'nowYear')" class="tag-y" :class="item == nowYear?'avtiveY':''"
					v-for="item in years" :key="item">{{ item }}</view>
			</view>
			<view class="screen-list">
				<view @click="activeType(item,'nowType')" class="tag-y" :class="item == nowType?'avtiveY':''"
					v-for="item in types" :key="item">{{ item }}</view>
			</view>
		</view>
		<div class="content">
			<view class="list" v-for="item in list" :key="item.id" @click="goDetails(item.id)">
				<image class="cover" :src="item.coverImg" mode="aspectFill"></image>
				<view style="padding: 0 10rpx;">
					<view class="title">{{ item.zhTitle }}</view>
					<view class="tag">评分：{{ item.score }}</view>
					<view class="desc">大小：{{ item.gameSize }}</view>
				</view>
			</view>
		</div>
		<up-loadmore :status="status" />
	</view>

</template>

<script>
	import config from "@/config/config.js"
	export default {
		data() {
			return {
				list: [],
				total: 1,
				pageSize: 6,
				pageNum: 1,
				keyWord: "",
				status: "loadmore",
				years: [],
				types: ["全部类型", "动作", "休闲", "模拟", "动作冒险", "角色扮演", "冒险独立", "冒险休闲独立角色扮演", "大型多人在线", "竞速", "体育", "策略"],
				nowYear: "全部年份",
				nowType: "全部类型",
				animetype: ["最多下载", "游戏大小","评分最高", "最近更新"],
				nowAnimeType: "最多下载",
			}
		},
		onLoad() {
			let date = new Date().getFullYear()
			for (var i = date; i > 2002; i--) {
				this.years.push(i)
			}
			this.years.unshift("全部年份")
			this.years.push("更早")
			this.getGameList()
		},
		onReachBottom() {
			if (this.pageNum * this.pageSize < this.total) {
				this.pageNum++;
				this.getGameList()
			}
		},
		methods: {
			searChange() {
				if (this.keyWord == "") {
					this.init()
					this.getGameList()
				}
			},
			activeType(item, key) {
				this[key] = item
				console.log(this.nowAnimeType);
				this.init()
				this.getGameList()
			},
			search() {
				this.init()
				this.getGameList()
			},
			init() {
				this.pageNum = 1
				this.pageSize = 6
				this.list = []
			},
			async getGameList() {
				this.status = 'loading';
				const res = await this.$http.getList({
					pageNum: this.pageNum,
					pageSize: this.pageSize,
					title: this.keyWord,
					releaseDate:this.nowYear == "全部年份"?"":this.nowYear,
					gameType:this.nowType == "全部类型"?"":this.nowType,
					sortType:this.nowAnimeType=="最多下载"?1:this.nowAnimeType=="评分最高"?2:this.nowAnimeType=="最近更新"?3:this.nowAnimeType=="游戏大小"?4:5
				})
				this.list = [...this.list, ...res.rows]
				this.total = res.total
				this.status = 'loadmore';
				if (this.pageNum * this.pageSize >= this.total) {
					this.status = 'nomore';
				}
			},
			goDetails(id) {
				uni.navigateTo({
					url: "/pages/gameDetails/gameDetails?id=" + id
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		padding: 12px 12px 0 12px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		flex-wrap: wrap;
	}

	.list {
		box-shadow: 4px 4px 10px #eaeaea;
		border-radius: 4px;
		width: 340rpx;
		height: 600rpx;
		margin-bottom: 20rpx;
		box-sizing: border-box;
		
		.title {
			height: 60rpx;
			line-height: 30rpx;
			font-size: 28rpx;
			/*以下内容是文字隐藏所必须的*/
			display: -webkit-box;
			word-break: break-all;
			/*换行规则*/
			text-overflow: ellipsis;
			/*最后使用省略号显示*/
			overflow: hidden;
			/*超出部分隐藏*/
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 2;
			/*能够显示几行*/
		}
		.tag {
			font-size: 24rpx;
			text-overflow: ellipsis;
			white-space: nowrap;
			overflow: hidden;
			color: #989898;
		}
		.desc {
			font-size: 24rpx;
			text-overflow: ellipsis;
			white-space: nowrap;
			overflow: hidden;
			color: #989898;
		}
	}

	.cover {
		width: 340rpx;
		height: 460rpx;
		border-radius: 4px 4px 0 0;
	}
	.screen-list {
		margin-top: 10rpx;
		display: flex;
		flex-wrap: nowrap;
		width: 100%;
		overflow-x: auto;
	
		.tag-y {
			min-width: 140rpx;
			line-height: 50rpx;
			width: 140rpx;
			height: 50rpx;
			margin: 6rpx 12rpx;
			text-align: center;
			border-radius: 12rpx;
		}
	
		.avtiveY {
			background: #9cc1f0;
			color: #0e70f0;
		}
	}
</style>