<!DOCTYPE html>
<html>
<head>
<title>IPS Server - Home</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- bootstrap -->
<link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-overrides.css" type="text/css"
	rel="stylesheet" />

<!-- libraries -->
<link href="css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet"
	type="text/css" />
<link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />

<!-- this page specific styles -->
<link rel="stylesheet" href="css/compiled/index.css" type="text/css"
	media="screen" />

<!-- open sans font -->
<link
	href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css' />

<!-- lato font -->
<link
	href='http://fonts.useso.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic'
	rel='stylesheet' type='text/css' />

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<!-- navbar -->
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<button type="button" class="btn btn-navbar visible-phone"
				id="menu-toggler">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a class="brand" href="index.html"><img src="img/logo.png" /></a>

			<ul class="nav pull-right">
				<li class="hidden-phone"><input class="search" type="text" />
				</li>
				<li class="notification-dropdown hidden-phone"><a href="#"
					class="trigger"> <i class="icon-warning-sign"></i> <span
						class="count">8</span>
				</a>
					<div class="pop-dialog">
						<div class="pointer right">
							<div class="arrow"></div>
							<div class="arrow_border"></div>
						</div>
						<div class="body">
							<a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
							<div class="notifications">
								<h3>You have 6 new notifications</h3>
								<a href="#" class="item"> <i class="icon-signin"></i> New
									user registration <span class="time"><i
										class="icon-time"></i> 13 min.</span>
								</a> <a href="#" class="item"> <i class="icon-signin"></i> New
									user registration <span class="time"><i
										class="icon-time"></i> 18 min.</span>
								</a> <a href="#" class="item"> <i class="icon-envelope-alt"></i>
									New message from Alejandra <span class="time"><i
										class="icon-time"></i> 28 min.</span>
								</a> <a href="#" class="item"> <i class="icon-signin"></i> New
									user registration <span class="time"><i
										class="icon-time"></i> 49 min.</span>
								</a> <a href="#" class="item"> <i class="icon-download-alt"></i>
									New order placed <span class="time"><i class="icon-time"></i>
										1 day.</span>
								</a>
								<div class="footer">
									<a href="#" class="logout">View all notifications</a>
								</div>
							</div>
						</div>
					</div></li>
				<li class="notification-dropdown hidden-phone"><a href="#"
					class="trigger"> <i class="icon-envelope-alt"></i>
				</a>
					<div class="pop-dialog">
						<div class="pointer right">
							<div class="arrow"></div>
							<div class="arrow_border"></div>
						</div>
						<div class="body">
							<a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
							<div class="messages">
								<a href="#" class="item"> <img src="img/contact-img.png"
									class="display" />
									<div class="name">Alejandra Galván</div>
									<div class="msg">There are many variations of available,
										but the majority have suffered alterations.</div> <span class="time"><i
										class="icon-time"></i> 13 min.</span>
								</a> <a href="#" class="item"> <img src="img/contact-img2.png"
									class="display" />
									<div class="name">Alejandra Galván</div>
									<div class="msg">There are many variations of available,
										have suffered alterations.</div> <span class="time"><i
										class="icon-time"></i> 26 min.</span>
								</a> <a href="#" class="item last"> <img
									src="img/contact-img.png" class="display" />
									<div class="name">Alejandra Galván</div>
									<div class="msg">There are many variations of available,
										but the majority have suffered alterations.</div> <span class="time"><i
										class="icon-time"></i> 48 min.</span>
								</a>
								<div class="footer">
									<a href="#" class="logout">View all messages</a>
								</div>
							</div>
						</div>
					</div></li>
				<li class="dropdown"><a href="#"
					class="dropdown-toggle hidden-phone" data-toggle="dropdown">
						Your account <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="personal-info.html">Personal info</a></li>
						<li><a href="#">Account settings</a></li>
						<li><a href="#">Billing</a></li>
						<li><a href="#">Export your data</a></li>
						<li><a href="#">Send feedback</a></li>
					</ul></li>
				<li class="settings hidden-phone"><a href="personal-info.html"
					role="button"> <i class="icon-cog"></i>
				</a></li>
				<li class="settings hidden-phone"><a href="signin.html"
					role="button"> <i class="icon-share-alt"></i>
				</a></li>
			</ul>
		</div>
	</div>
	<!-- end navbar -->

	<!-- sidebar -->
	<div id="sidebar-nav">
		<ul id="dashboard-menu">
			<li class="active">
				<div class="pointer">
					<div class="arrow"></div>
					<div class="arrow_border"></div>
				</div> <a href="index.html"> <i class="icon-home"></i> <span>Home</span>
			</a>
			</li>
			<li><a href="chart-showcase.html"> <i class="icon-signal"></i>
					<span>Charts</span>
			</a></li>
			<li><a class="dropdown-toggle" href="#"> <i
					class="icon-group"></i> <span>Users</span> <i
					class="icon-chevron-down"></i>
			</a>
				<ul class="submenu">
					<li><a href="user-list.html">User list</a></li>
					<li><a href="new-user.html">New user form</a></li>
					<li><a href="user-profile.html">User profile</a></li>
				</ul></li>
			<li><a class="dropdown-toggle" href="#"> <i
					class="icon-edit"></i> <span>Forms</span> <i
					class="icon-chevron-down"></i>
			</a>
				<ul class="submenu">
					<li><a href="form-showcase.html">Form showcase</a></li>
					<li><a href="form-wizard.html">Form wizard</a></li>
				</ul></li>
			<li><a href="gallery.html"> <i class="icon-picture"></i> <span>Gallery</span>
			</a></li>
			<li><a href="calendar.html"> <i class="icon-calendar-empty"></i>
					<span>Calendar</span>
			</a></li>
			<li><a href="tables.html"> <i class="icon-th-large"></i> <span>Tables</span>
			</a></li>
			<li><a class="dropdown-toggle ui-elements" href="#"> <i
					class="icon-code-fork"></i> <span>UI Elements</span> <i
					class="icon-chevron-down"></i>
			</a>
				<ul class="submenu">
					<li><a href="ui-elements.html">UI Elements</a></li>
					<li><a href="icons.html">Icons</a></li>
				</ul></li>
			<li><a href="personal-info.html"> <i class="icon-cog"></i> <span>My
						Info</span>
			</a></li>
			<li><a class="dropdown-toggle" href="#"> <i
					class="icon-share-alt"></i> <span>Extras</span> <i
					class="icon-chevron-down"></i>
			</a>
				<ul class="submenu">
					<li><a href="code-editor.html">Code editor</a></li>
					<li><a href="grids.html">Grids</a></li>
					<li><a href="signin.html">Sign in</a></li>
					<li><a href="signup.html">Sign up</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- end sidebar -->


	<!-- main container -->
	<div class="content">

		<!-- settings changer -->
		<!-- 
		<div class="skins-nav">
			<a href="#" class="skin first_nav selected"> <span class="icon"></span><span
				class="text">Default skin</span>
			</a> <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
				<span class="icon"></span><span class="text">Dark skin</span>
			</a>
		</div>
		 -->

		<div class="container-fluid">

			<!-- upper main stats -->
			<div id="main-stats">
				<div class="row-fluid stats-row">
					<div class="span3 stat">
						<div class="data">
							<span class="number">7</span> users
						</div>
						<span class="date">Today</span>
					</div>
					<div class="span3 stat">
						<div class="data">
							<span class="number">3</span> managers
						</div>
						<span class="date">February 2014</span>
					</div>
					<div class="span3 stat">
						<div class="data">
							<span class="number">10</span> public interest points
						</div>
						<span class="date">This week</span>
					</div>
					<div class="span3 stat last">
						<div class="data">
							<span class="number">10</span> private interest points
						</div>
						<span class="date">last 30 days</span>
					</div>
				</div>
			</div>
			<!-- end upper main stats -->

			<div id="pad-wrapper">

				<!-- statistics chart built with jQuery Flot -->
				<div class="row-fluid chart">
					<h4>
						Map Data
						<div class="btn-group pull-right">
							<button class="glow left">PATH</button>
							<button class="glow right">INTEREST POINT</button>
						</div>
					</h4>
					<div class="span12">
						<div id="statsChart" style="width: 1000px; height: 700px;"></div>
						<div id="heatmapContainer" style="width: 1000px; height: 700px;margin-top: -700px"></div>
					</div>
				</div>
				<!-- end statistics chart -->

				<!-- UI Elements section -->
				<div class="row-fluid section ui-elements">
					<h4>Datac Composition</h4>
					<div class="span5 knobs">
						<div class="knob-wrapper">
							<input id="iprate" type="text" value="50" class="knob"
								data-thickness=".3" data-inputcolor="#333"
								data-fgcolor="#30a1ec" data-bgcolor="#d4ecfd" data-width="150" />
							<div class="info">
								<div class="param">
									<span class="line blue"></span> Private Interest Points
								</div>
							</div>
						</div>
						<div class="knob-wrapper">
							<input id="userrate" type="text" value="75" class="knob second"
								data-thickness=".3" data-inputcolor="#333"
								data-fgcolor="#3d88ba" data-bgcolor="#d4ecfd" data-width="150" />
							<div class="info">
								<div class="param">
									<span class="line blue"></span> Normal Users
								</div>
							</div>
						</div>
					</div>

					<div class="span6 showcase" style="margin-top: -55px">
						<h4>Interest Points History</h4>
						<div class="span12">
							<div id="historyChart" style="width: 500px; height: 200px;"></div>
						</div>
					</div>

					<!--  
					<div class="span6 showcase">
						<div class="ui-sliders">
							<div class="slider slider-sample1 vertical-handler"></div>
							<div class="slider slider-sample2"></div>
							<div class="slider slider-sample3"></div>
						</div>
						<div class="ui-group">
							<a class="btn-flat inverse">Large Button</a> <a
								class="btn-flat gray">Large Button</a> <a
								class="btn-flat default">Large Button</a> <a
								class="btn-flat primary">Large Button</a>
						</div>

						<div class="ui-group">
							<a class="btn-flat icon"> <i class="tool"></i> Icon button
							</a> <a class="btn-glow small inverse"> <i class="shuffle"></i>
							</a> <a class="btn-glow small primary"> <i class="setting"></i>
							</a> <a class="btn-glow small default"> <i class="attach"></i>
							</a>
							<div class="ui-select">
								<select>
									<option selected="" />Dropdown
									<option />Custom selects
									<option />Pure css styles
								</select>
							</div>

							<div class="btn-group">
								<button class="glow left">LEFT</button>
								<button class="glow right">RIGHT</button>
							</div>
						</div>
					</div>
					
				-->
				</div>

				<!-- end UI elements section -->

				<!-- UI Elements section -->
				<div class="row-fluid section ui-elements">
				    <h4>Relationship Graph</h4>
					<div id="container" style="width: 1000px;height: 700px"></div>
				</div>
				


			</div>
			<!-- end table sample -->
		</div>
	</div>


	<!-- scripts -->
	<script src="js/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery-ui-1.10.2.custom.min.js"></script>
	<!-- knob -->
	<script src="js/jquery.knob.js"></script>
	<!-- flot charts -->
	<script src="js/jquery.myflot.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.js"></script>
	<script src="js/theme.js"></script>
	<!-- relationship -->
	<script src="build/sigma.min.js"></script>
	<script src="build/plugins/sigma.parsers.json.min.js"></script>
	<script src="build/plugins/sigma.plugins.dragNodes.min.js"></script>
	<script src="build/plugins/sigma.renderers.customEdgeShapes.min.js"></script>

	<!-- heatmap -->
    <script src="heatmap/build/heatmap.js"></script>
    
	<script type="text/javascript">
	
	// create configuration object
	var config = {
		container : document.getElementById("heatmapContainer"),
		//container : document.getElementById("statsChart"),
		radius : 100,
		maxOpacity : .3,
		minOpacity : 0,
		blur : .75,
	/*	gradient: {
			// enter n keys between 0 and 1 here
			// for gradient color customization
			'.5': 'blue',
			'.55': 'green',
			'.9': 'yellow',
			'1': 'red',
	    }*/
	};
	// create heatmap with configuration
	var heatmapInstance = h337.create(config);
	var data=${requestScope.lstHeatPoint };
	heatmapInstance.addData(data);
	
	sigma.parsers.json('data1.json', {
		container : 'container'
	}, function(s) {
		var dragListener = sigma.plugins.dragNodes(s, s.renderers[0]);
	});

		$(function() {
			

			var userrate = ${ requestScope.userrate};
			document.getElementById("iprate").setAttribute("value", 26.989999);
			document.getElementById("userrate").setAttribute("value", userrate);

			// jQuery Knobs
			$(".knob").knob();


			tem = ${requestScope.lstMap};

			var map = new Array;
			var j = 0;
			for ( var i = 0; i < tem.length; i++) {
				map[j] = [ tem[i].startx, tem[i].starty ];
				map[j + 1] = [ tem[i].endx, tem[i].endy ];
				j = j + 2;
			}

			tem = ${requestScope.lstPath};

			var path = new Array;
			j = 0;
			for ( var i = 0; i < tem.length; i++) {
				path[j] = [ tem[i].startx, tem[i].starty ];
				path[j + 1] = [ tem[i].endx, tem[i].endy ];
				j = j + 2;
			}

			// jQuery Flot Chart
			var visits = [ [ 1, 50 ], [ 1.5, 40 ], [ 3, 45 ], [ 4, 23 ],
					[ 5, 55 ], [ 6, 65 ], [ 6, 61 ], [ 2.5, 70 ], [ 9, 65 ],
					[ 10, 75 ], [ 11, 57 ], [ 12, 59 ] ];
			var visitors = [ [ 1, 25 ], [ 2, 50 ], [ 3, 23 ], [ 4, 48 ],
					[ 5, 38 ], [ 6, 40 ] ];

			var historyplot = $.plot($("#historyChart"), [ {
				data : visitors,
				label : "Appended Interest Points"
			} ], {
				series : {
					lines : {
						show : true,
						lineWidth : 1,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.1
							}, {
								opacity : 0.13
							} ]
						}
					},
					points : {
						show : false,
						lineWidth : 2,
						radius : 3
					},
					shadowSize : 0,
					stack : true
				},
				grid : {
					hoverable : true,
					clickable : true,
					tickColor : "#f9f9f9",
					borderWidth : 0
				},
				legend : {
					// show: false
					labelBoxBorderColor : "#fff"
				},
				colors : [ "#a7b5c5", "#30a0a0" ],
				xaxis : {
					ticks : 3,
					tickDecimals : 0,
					font : {
						size : 12,
						color : "#9da3a9"
					}
				},
				yaxis : {
					ticks : 3,
					tickDecimals : 0,
					font : {
						size : 12,
						color : "#9da3a9"
					}
				}
			});

			var plot = $.myplot($("#statsChart"), [ map, path ], {
				series : {
					lines : {
						show : true,
						lineWidth : 1,
						fill : false,
						fillColor : {
							colors : [ {
								opacity : 0.1
							}, {
								opacity : 0.13
							} ]
						}
					},
					points : {
						show : false,
						lineWidth : 2,
						radius : 3
					},
					shadowSize : 0,
					stack : true
				},
				grid : {
					hoverable : true,
					clickable : false,
					tickColor : "#f9f9f9",
					borderWidth : 0
				},
				legend : {
					// show: false
					labelBoxBorderColor : "#fff"
				},
				colors : [ "#a7b5c5", "#11ee34" ],
				xaxis : {
					ticks : 0,
					tickDecimals : 0,
					font : {
						size : 12,
						color : "#9da3a9"
					}
				},
				yaxis : {
					ticks : 0,
					tickDecimals : 0,
					length : 0,
					font : {
						size : 12,
						color : "#9da3a9"
					}
				}
			});
			

			function showTooltip(x, y, contents) {
				$('<div id="tooltip">' + contents + '</div>').css({
					position : 'absolute',
					display : 'none',
					top : y - 30,
					left : x - 50,
					color : "#fff",
					padding : '2px 5px',
					'border-radius' : '6px',
					'background-color' : '#000',
					opacity : 0.80
				}).appendTo("body").fadeIn(200);
			}

			var previousPoint = null;
			$("#statsChart")
					.bind(
							"plothover",
							function(event, pos, item) {
								if (item) {
									if (previousPoint != item.dataIndex) {
										previousPoint = item.dataIndex;

										$("#tooltip").remove();
										var x = item.datapoint[0].toFixed(2), y = item.datapoint[1]
												.toFixed(2);

										showTooltip(item.pageX, item.pageY, "("
												+ x + "," + y + ")");
									}
								} else {
									$("#tooltip").remove();
									previousPoint = null;
								}
							});
		});
		
		
		
	</script>

</body>
</html>