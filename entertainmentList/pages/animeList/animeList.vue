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
				<image style="width: 230rpx;height: 350rpx;border-radius: 8rpx;" :src="item.coverImg" mode="aspectFill">
				</image>
				<view class="tag">{{ item.updateState }}</view>
				<view class="title">{{ item.title }}</view>
				<view class="desc">{{ item.alias }}</view>
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
				types: ["全部类型", "搞笑", "运动", "励志", "武侠", "特摄", "热血", "战斗", "竞技", "校园", "青春", "爱情", "冒险", "后宫", "百合", "治愈",
					"萝莉", "魔法", "悬疑", "推理", "奇幻", "神魔", "恐怖", "血腥", "机战", "战争", "犯罪", "社会", "职场", "剧情", "伪娘", "耽美",
					"歌舞", "肉番", "美少女", "吸血鬼", "泡面番", "欢乐向"
				],
				nowYear: "全部年份",
				nowType: "全部类型",
				animetype: ["国产动漫", "日本动漫", "动漫电影"],
				nowAnimeType: "国产动漫",
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
					url: "/pages/amineDetails/amineDetails?id=" + item.id + "&type=" + this.nowAnimeType+"&index=0"
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
				let res = ""
				if (this.nowAnimeType == "国产动漫") {
					res = await this.$http.getChAnimeList({
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						title: this.keyWord,
						animeYear: this.nowYear == "全部年份" ? "" : this.nowYear,
						animeType: this.nowType == "全部类型" ? "" : this.nowType
					})

				} else if (this.nowAnimeType == "日本动漫") {
					res = await this.$http.getMoveList({
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						title: this.keyWord,
						animeYear: this.nowYear == "全部年份" ? "" : this.nowYear,
						animeType: this.nowType == "全部类型" ? "" : this.nowType
					})

				} else if (this.nowAnimeType == "动漫电影") {
					res = await this.$http.getAnimeMoiveList({
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						title: this.keyWord,
						animeYear: this.nowYear == "全部年份" ? "" : this.nowYear,
						animeType: this.nowType == "全部类型" ? "" : this.nowType
					})
				} else {
					uni.showToast({
						title: "出错了",
						icon: 'error'
					})
				}
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