<%@ page language="java" pageEncoding="UTF-8"%>
<div class="container gallery-container">
	<h1>制度墙</h1>
	<div class="tz-gallery">
		<div id="app" class="row">
			<template v-for="photo in photos">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<a class="lightbox" v-bind:href="photo.url"> <img
							v-bind:src="photo.url" alt="Park">
						</a>
						<div class="caption">
							<h3>{{photo.title}}</h3>
							<p>{{photo.remark}}</p>
						</div>
					</div>
				</div>
			</template>
			<!--
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<a class="lightbox" href="photo/park.jpg"> <img
						src="photo/park.jpg" alt="Park">
					</a>
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<a class="lightbox" href="photo/bridge.jpg"> <img
						src="photo/bridge.jpg" alt="Bridge">
					</a>
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<a class="lightbox" href="photo/tunnel.jpg"> <img
						src="photo/tunnel.jpg" alt="Tuneel">
					</a>
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<a class="lightbox" href="photo/coast.jpg"> <img
						src="photo/coast.jpg" alt="Coast">
					</a>
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<a class="lightbox" href="photo/rails.jpg"> <img
						src="photo/rails.jpg" alt="Rails">
					</a>
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<a class="lightbox" href="photo/traffic.jpg"> <img
						src="photo/traffic.jpg" alt="Traffic">
					</a>
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
					</div>
				</div>
			</div>

			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<a class="lightbox" href="photo/coast.jpg"> <img
						src="photo/coast.jpg" alt="Coast">
					</a>
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<a class="lightbox" href="photo/rails.jpg"> <img
						src="photo/rails.jpg" alt="Rails">
					</a>
					<div class="caption">
						<h3>Thumbnail label</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
					</div>
				</div>
			</div>
			  
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<a class="lightbox" href="photo/traffic.jpg"> <img
							src="photo/traffic.jpg" alt="Traffic">
						</a>
						<div class="caption">
							<h3>Thumbnail label</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua.</p>
						</div>
					</div>
				</div>
				-->
			<!-- //end more -->

		</div>

	</div>

</div>


