
var editorwidth 	= 800 ;
var editorheight	= 425 ;


var imgflag 		 = 0 ;
var tableflag		 = 0 ;
var clearflag		 = 1 ;
var cutflag 		 = 1 ;
var undoflag 		 = 1 ;
var alignflag 	  	 = 1 ;
var bulletflag		 = 1 ;
var indentflag 		 = 1 ;
var linkflag 	 	 = 0 ;
var symbolsflag 	 = 0 ;
var ruleflag 		 = 0 ;
var helpflag 		 = 0 ;
var font_typeflag 	 = 0 ;
var font_styleflag	 = 0 ;
var fore_colorflag	 = 0 ;
var showhtmlflag 	 = 0 ;
var clean_html_flag 	 = 0 ;
var spellcheck_flag 	 = 0 ;
var css_flag 		 = 0 ;
var unlink_flag 	 = 0 ;
var remove_flag 	 = 1 ;
var xhtml_flag 		 = 0 ;
var save_flag 		 = 0 ;

var iconspath 		 = "images";  				// for default path
var spell_check_file 	 = "samples/jsp/spell.jsp";		// To assign spell check sample file 
var save_url 		 = "samples/jsp/save_file_content.jsp"; // Where the editor content should be saved

var l_str_sendBody= "";
var input_content = "";
var browser_type  = 0;
var editor="";
var temp_str_cnt = "";
var temp_str_cnt1 = "";
var CarriageReturn = true; 	// When the user presses <ENTER> inserts a <BR> tag instead of a <P> 

function setContent(temp_content){
	input_content =temp_content;
	temp_str_cnt = input_content;
	temp_str_cnt1 = input_content;
}

function replace(){

   var   temp_str_qt = /"/gi;  //for replacing quote
   var   temp_str_quot = "&quot;";  //for replacing quote
   var   temp_str_amp = /&/gi;  //for replacing &
   var   temp_str_amph = "&amp;";  //for replacing &amp

   temp_str_cnt1 = temp_str_cnt1.replace( temp_str_amp, temp_str_amph ); //replacing & with &amp;
   temp_str_cnt1 = temp_str_cnt1.replace( temp_str_qt, temp_str_quot ); // replacing " with &quot;
}

function getContent(mode)
{
	  //for checking the html mode
  if (!frame1.isHTMLMode())
	return;
/*
	If you want pure Xhtml content, call frame1.getXHTML("1") otherwise frame1.htmlContent()
*/

	var content1 =	"";
	if(browser_type ==3)
	{
		content1 = frame1.htmlContent();
	}
	else if(browser_type ==2)
	{
		frame1.close_child();
		content1 =	frame1.htmlContent();
	}
		//for adding html & body tag
		//content1 = "<HTML><HEAD></HEAD><BODY>"+content1+"</BODY></HTML>";
		//storing ouput value in hid_out_content value
		document.form1.hid_out_content.value = content1 ;
		//comment the below line before implementation

		if(mode=="1")
			document.form1.submit();
		else
			document.write(document.form1.hid_out_content.value);

}


function getTextOnly(){
	//for checking the html mode
	if (!frame1.isHTMLMode())
		return;

	var content1 =	"";
	if(browser_type ==3)
	{
		content1 = frame1.getTextContent();
	}
	else if(browser_type ==2)
	{
		frame1.close_child();
		content1 =	frame1.getTextContent();
	}
	document.write(content1);
}



function setEditor(path){
	editor='<IFRAME name = "frame1" id=editForm src="'+path+'editor.html" scrolling=no width='+(editorwidth)+' height='+(editorheight)+' frameborder=0></IFRAME>';
	var browser_message = "Not Supported";
	if(navigator.userAgent.indexOf("Netscape")!= -1 )
	{
		if(navigator.userAgent.indexOf("7") != -1)
		{
			document.write(editor);
			browser_type=2;
		}
		else
		{
			document.write(browser_message);
			browser_type =1;
			//submit ="";
		}
	}
	else if(navigator && navigator.userAgent && navigator.userAgent.indexOf && navigator.userAgent.indexOf('Gecko') != -1)
	{
		 document.write(editor);
		 browser_type=2;
	}
	else if(navigator.userAgent.indexOf("MSIE")!= -1)
	{
		document.write(editor);
		browser_type=3;
	}
	else if(navigator.userAgent.indexOf("Opera")!= -1)
	{
		document.write(browser_message);
		browser_type =4;
		//submit ="";
	}
	else
	{
		document.write(browser_message);
		browser_type =5;
		//submit ="";
	}

}

function setParam(){
	l_str_sendBody= temp_str_cnt;
	document.write('<input 		id="sendBody"			value="'+temp_str_cnt1+'"     	type="hidden">');
	document.write('<input 		id="img_flag"			value="'+imgflag+'"     	type="hidden">');
	document.write('<input 		id="table_flag"			value="'+tableflag+'"     	type="hidden">');
	document.write('<input 		id="clear_flag"			value="'+clearflag+'"     	type="hidden">');
	document.write('<input 		id="cut_flag"			value="'+cutflag+'"     	type="hidden">');
	document.write('<input 		id="undo_flag"			value="'+undoflag+'"     	type="hidden">');
	document.write('<input 		id="align_flag"			value="'+alignflag+'"     	type="hidden">');
	document.write('<input 		id="bullet_flag"			value="'+bulletflag+'"     	type="hidden">');
	document.write('<input 		id="indent_flag"			value="'+indentflag+'"     	type="hidden">');
	document.write('<input 		id="link_flag"			value="'+linkflag+'"     	type="hidden">');
	document.write('<input 		id="symbols_flag"			value="'+symbolsflag+'"     	type="hidden">');
	document.write('<input 		id="rule_flag"			value="'+ruleflag+'"     	type="hidden">');
	document.write('<input 		id="help_flag"			value="'+helpflag+'"     	type="hidden">');
	document.write('<input 		id="font_type_flag"			value="'+font_typeflag+'"     	type="hidden">');
	document.write('<input 		id="font_style_flag"			value="'+font_styleflag+'"     	type="hidden">');
	document.write('<input 		id="fore_color_flag"			value="'+fore_colorflag+'"     	type="hidden">');
	document.write('<input 		id="showhtml_flag"			value="'+showhtmlflag+'"     	type="hidden">');
	document.write('<input 		id="icons_path"			value="'+iconspath+'"     	type="hidden">');
	document.write('<input 		id="editor_width"			value="'+editorwidth+'"     	type="hidden">');
	document.write('<input 		id="editor_height"			value="'+editorheight+'"     	type="hidden">');

	document.write('<input 		id="clean_html_flag"	name="clean_html_flag"	value="'+clean_html_flag+'"     	type="hidden">');
	document.write('<input 		id="spellcheck_flag" 	name="spellcheck_flag"	value="'+spellcheck_flag+'"     	type="hidden">');
	document.write('<input 		id="css_flag" 			name="css_flag"			value="'+css_flag+'"     	type="hidden">');
	document.write('<input 		id="unlink_flag" 		name="unlink_flag"		value="'+unlink_flag+'"     	type="hidden">');
	document.write('<input 		id="remove_flag" 		name="remove_flag"		value="'+remove_flag+'"     	type="hidden">');
	document.write('<input 		id="xhtml_flag" 		name="xhtml_flag"		value="'+xhtml_flag+'"     	type="hidden">');
	document.write('<input 		id="save_flag" 		name="save_flag"		value="'+save_flag+'"     	type="hidden">');


	document.write('<input 		id="font_list" 			name="font_list"		value=""     	type="hidden">');
	document.write('<input 		id="hid_color" 			name="hid_color"		value="" 		type="hidden">');
	document.write('<input 		id="hid_frame_color" 	name="hid_frame_color"	value="" 		type="hidden">');
	document.write('<input 		id="font_name" 			name="font_name"		value=""     	type="hidden">');
	document.write('<input 		id="font_size" 			name="font_size"		value=""     	type="hidden">');
	document.write('<input 		id="link_name" 			name="link_name"		value="www.akmin.com,www.sitegalore.com,www.sitegaloreplus.com"     	type="hidden">');
	document.write('<input 		id="def_link_name" 		name="def_link_name"	value="www.sitegaloreplus.com"     	type="hidden">');
	document.write('<input 		id="css_list"	 		name="css_list"			value="header1,header2,text,pageheader,boldtext,tableheader1,tablecolor,italictext,textbg,tableheader2,tableheader3,graytext,smalltext,header3,header4,graytextbold,redtext,footertext"     	type="hidden">');
	document.write('<input 		id="cssurl" 			name="cssurl"	value="css/sample.css"     	type="hidden">');

	document.write('<input 		id="spell_check_file" 			name="spell_check_file"	value="'+spell_check_file+'"     	type="hidden">');
	document.write('<input 		id="save_url" 			name="save_url"	value="'+save_url+'"     	type="hidden">');
	document.write('<input 		id="CarriageReturn" 		name="CarriageReturn"	value="'+CarriageReturn+'"     	type="hidden">');
}

function setEditlet(path){
	setEditor(path);
	replace();
	setParam();
}