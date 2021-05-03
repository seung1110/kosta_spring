<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.uploadResult{
		width: 100%;
		background-color: gray;
	}
	
	.uploadResult ul{
		display:flex;
		flex-flow:row;
		justify-content : center;
		align-items : center;
	}
	
	.uploadResult ul li{
		list-style: none;
		padding: 10px;
	}
	
	.uploadResult ul li img{
		width : 20px;
	}
</style>
</head>
<body>
<h1>Upload with Ajax</h1>

<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple>
</div>
	<button id="uploadBtn">Upload</button>
<div class="uploadResult">
	<ul></ul>
</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880; // 5MB
	
			function checkExtension(fileName,fileSize){
				if(fileSize >= maxSize){
					alert("파일 크기 초과");
					return false;
				}
				
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}
			
			var cloneObj = $(".uploadDiv").clone();
			
			$("#uploadBtn").on("click",function(e){
				var formData = new FormData();
				
				var inputFile = $("input[name='uploadFile']");
				
				var files = inputFile[0].files;
				
				
				
				for(var i = 0; i < files.length;i++){
					console.log(i);
					if(!checkExtension(files[i].name,files[i].size)){
						return false;
					}
					
					formData.append("uploadFile",files[i]);
				}
				
				$.ajax({
					url:"/uploadAjaxAction",
					processData:false,
					contentType:false,
					data : formData,
					type : "post",
					dataType : "json",
					success : function(result){
						console.log(result);
						showUploadFile(result);
						$(".uploadDiv").html(cloneObj.html());
					}
					
				});
				
			})
			
			var uploadResult = $(".uploadResult ul");
			
			function showUploadFile(uploadResultArr){
				var str = "";
				
				$(uploadResultArr).each(function(i,obj){
					str+="<li>"+obj.fileName+"</li>";
				})
				
				uploadResult.append(str);
			}
		});
	</script>
</body>
</html>