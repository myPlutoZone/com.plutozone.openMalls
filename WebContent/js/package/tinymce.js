/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2016 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2016 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.common
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: /common/package/tinymce/tinymce.js
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20160108134200][pluto#plutozone.com][CREATE: Initial Release]
 *				: [20190116153600][pluto#plutozone.com][UPDATE: Change Package]
 */

// tinymce.init({selector:'textarea'});
tinymce.init({
	selector: "textarea#content",
	theme: "modern",
	//width: 100%,
	height: 200,
	plugins: [
		/*
		"advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker",
		"searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
		"save table contextmenu directionality emoticons template paste textcolor"
		*/
		"advlist autolink link lists charmap print preview hr anchor pagebreak",
		"searchreplace visualblocks visualchars code fullscreen insertdatetime nonbreaking",
		"save table contextmenu directionality template paste textcolor"
	],
	//content_css: "css/content.css",
	//toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons",
	toolbar: "insertfile | bold italic | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link | print preview fullpage",
	style_formats: [
		{title: 'Bold text', inline: 'b'},
		{title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
		{title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
		{title: 'Example 1', inline: 'span', classes: 'example1'},
		{title: 'Example 2', inline: 'span', classes: 'example2'},
		{title: 'Table styles'},
		{title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
	]
 }); 