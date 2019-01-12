$(function(){
	// 两个排行榜内容切换tab切换
	$('.ip_ranking_article_tab li').eq(0).addClass('on').siblings().removeClass('on');
	
	// var page = 1;
	// var size = 50;

	// // getData(page);
	// $('.more_btn').click(function(){
	// 	page += 1;
	// 	getData(page);
	// })
	
	// // 请求数据
	// function getData(page) {
	// 	$.ajax({
	// 		url:domain + '/listArticle/selectAllListArticle',
	// 		type: 'post',
	// 		data: {
	// 			page: page,
	// 			size: size
	// 		},
	//         success:function (res){
	// 			if(res.status == 'success'){
	// 				var list_content = $('.list_content');
	// 				var data = res.data.data;
	// 				res.data.total <= page*size ? $('.more_btn').css({'display': 'none'}) : null;
	// 				data.forEach(function(item,index){
	// 					var num = (index+1+(page-1)*size);
	// 					if(num == 1) {
	// 						num = null;
	// 						num = '<img src="/images/ipEvaluation/ipRanking/no_1.png">'
	// 					} else if(num == 2) {
	// 						num = null;
	// 						num = '<img src="/images/ipEvaluation/ipRanking/no_2.png">'
	// 					} else if(num == 3) {
	// 						num = null;
	// 						num = '<img src="/images/ipEvaluation/ipRanking/no_3.png">'
	// 					} else {
	// 						num = num;
	// 					}
	// 					$('<ul>'+
	// 						'<li class="title"><span>'+ num +'</span><a target="_blank" href="'+ item.Link + '">'+ item.Title  +'</a></li>'+
	// 						'<li>'+ item.Theme +'</li>'+
	// 						'<li>'+ item.Creativity +'</li>'+
	// 						'<li>'+ item.Originality +'</li>'+
	// 						'<li>'+ item.TotalIndex +'</li>'+
	// 					'</ul>').appendTo(list_content);
	// 				})
	// 			}
	//         }
	// 	})
	// }
	
	// 数据说明中的返回按钮，返回前一个页面
	// $('.go').click(function() {
	// 	var url = document.referrer;
	// 	$('.go').attr('href',url);
	// })


})