<template>
	<view class="content">
		<up-sticky offset-top="0" bgColor="white" style="padding: 10rpx 24rpx;">
			<up-search @change="searChange" @search="search" shape="square" placeholder="请输入关键字"
				v-model="keyWord"></up-search>
		</up-sticky>
		<view class="screen">
			<view class="screen-list">
				<view @click="activeType(item,'nowAnimeType')" class="tag-y" :class="item == nowAnimeType?'avtiveY':''"
					v-for="item in animetype" :key="item">{{ item }}</view>
			</view>
			<view class="screen-list">
				<view @click="activeType(item,'nowArea')" class="tag-y" :class="item == nowArea?'avtiveY':''"
					v-for="item in areas" :key="item">{{ item }}</view>
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
		<view class="list">
			<view @click="goDeatils(item)" class="list-box" v-for="(item,index) in list" :key="index">
				<image style="width: 230rpx;height: 350rpx;border-radius: 8rpx;" :src="item.image" mode="aspectFill">
				</image>
				<view class="tag">{{ item.movieEdition }}</view>
				<view class="title">{{ item.movieTitle }}</view>
				<view class="desc">{{ item.movieDesc2 }}</view>
			</view>
		</view>
		<up-loadmore :status="status" />
	</view>
</template>

<script>
	//定义变量用于接收webview对象
	var GOOGLE_MAP_STYLE;
	//定义变量用于接收屏幕宽度
	var SYSTEM_WIDTH;
	export default {
		data() {
			return {
				list: [],
				total: 1,
				pageSize: 20,
				pageNum: 1,
				keyWord: "",
				status: "loadmore",
				years: [],
				types: ["全部类型","科幻片","恐怖片","战争片","动作片","爱情片","解说喜剧片","记录片","剧情片","邵氏电影","悬疑片","犯罪片"],
				areas: ["全部地区","大陆","香港","台湾","美国","法国","英国","日本","韩国","德国","泰国","印度","意大利","西班牙","加拿大","其他"],
				nowArea:"全部地区",
				nowYear: "全部年份",
				nowType: "全部类型",
				animetype: ["电影", "电视剧", "综艺"],
				nowAnimeType: "电影",
			}
		},
		onLoad() {
			this.getMoveList()
			let date = new Date().getFullYear()
			for (var i = date; i > 2002; i--) {
				this.years.push(i)
			}
			this.years.unshift("全部年份")
			this.years.push("更早")
		},
		onReachBottom() {
			if (this.pageNum * this.pageSize < this.total) {
				this.pageNum++;
				this.getMoveList()
			}
		},
		methods: {
			goDeatils(item) {
				uni.navigateTo({
					url: "/pages/moveDetails/moveDetails?id=" + item.id + "&index=0"
				})
			},
			activeType(item, key) {
				this[key] = item
				console.log(this.nowAnimeType);
				this.init()
				this.getMoveList()
			},
			searChange() {
				if (this.keyWord == "") {
					this.init()
					this.getMoveList()
				}
			},
			search() {
				this.init()
				this.getMoveList()
			},
			init() {
				this.pageNum = 1
				this.pageSize = 10
				this.list = []
			},
			async getMoveList() {
				let res = await this.$http.getMoiveList({
					pageNum: this.pageNum,
					pageSize: this.pageSize,
					movieTitle: this.keyWord,
					movieArea: this.nowArea == "全部地区" ? "" : this.nowArea,
					movieYear: this.nowYear == "全部年份" ? "" : this.nowYear,
					movieType: this.nowType == "全部类型" ? "" : this.nowType,
					category:this.nowAnimeType
				})
				console.log(res);
				this.list = [...this.list, ...res?.rows]
				this.total = res.total
				this.status = 'loadmore';
				if (this.pageNum * this.pageSize >= this.total) {
					this.status = 'nomore';
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		padding-bottom: 40rpx;
	}

	.list {
		width: 100%;
		display: flex;
		flex-wrap: wrap;
		padding: 0 12rpx;
		box-sizing: border-box;

		.list-box:nth-child(3n) {
			margin-right: 0;
		}

		.list-box {
			width: 230rpx;
			height: 450rpx;
			margin-top: 20rpx;
			position: relative;
			margin-right: 15rpx;

			.tag {
				position: absolute;
				width: 100%;
				top: 306rpx;
				right: 10rpx;
				text-align: right;
				color: white;
				font-size: 24rpx;
			}

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

			.desc {
				font-size: 24rpx;
				text-overflow: ellipsis;
				white-space: nowrap;
				overflow: hidden;
				color: #989898;
			}
		}

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