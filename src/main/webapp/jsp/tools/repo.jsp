<%@ page language="java" pageEncoding="UTF-8"%>
<div class="container">
	<div class="row">
		<div class="col-md-3">
			<div id="tree"></div>
		</div>
		<div class="col-md-9">
			<div class="row">
				<div class="col-md-1">
					<button type="button" class="btn btn-default">月份</button>
				</div>
				<div class="col-md-11">
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-default">一月</button>
						<button type="button" class="btn btn-default">二月</button>
						<button type="button" class="btn btn-default">三月</button>
						<button type="button" class="btn btn-default">四月</button>
						<button type="button" class="btn btn-default">五月</button>
						<button type="button" class="btn btn-default">六月</button>
						<button type="button" class="btn btn-default">七月</button>
						<button type="button" class="btn btn-default">八月</button>
						<button type="button" class="btn btn-default">九月</button>
						<button type="button" class="btn btn-default">十月</button>
						<button type="button" class="btn btn-default">十一月</button>
						<button type="button" class="btn btn-default">十二月</button>
					</div>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-1">
					<button type="button" class="btn btn-default">关键字</button>
				</div>
				<div class="col-md-11">
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-default">经济</button>
						<button type="button" class="btn btn-default">文化</button>
						<button type="button" class="btn btn-default">教育</button>
					</div>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-6">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for..."> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button">Go!</button>
						</span>
					</div>
				</div>
				<div class="col-md-6"></div>
			</div>
			<hr/>
			<div class="row">
				<div class="col-md-11">
					<table id="tb_departments"></table>
				</div>
				<div class="col-md-1">
				</div>
			</div>
		</div>
	</div>
</div>
