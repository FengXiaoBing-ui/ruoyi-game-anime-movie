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
				<view class="padding-xs flex flex-direction">
					<view class="text-bold text-cut text-lg">{{ item.zhTitle }}</view>
					<view class="">{{ item.title }}</view>
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
				animetype: ["最多下载", "评分最高", "最近更新"],
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
		width: 170px;
		height: 600rpx;
		margin-bottom: 20px;
		box-sizing: border-box;
	}

	.cover {
		width: 170px;
		height: 230px;
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