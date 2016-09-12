
MultipleStatusView使用说明

MultipleStatusView 继承 RelativeLayout,所以可以像RelativeLayout那样使用

1,  	       
<code>
 		
		第一种用法：
        内容视图可以直接在app:contentView属性指定layout文件，如下。

	 <com.charles.multiple_status_view.MultipleStatusView
        android:id="@+id/main_multiplestatusview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:content_View="@layout/main_content"
        app:empty_View="@layout/custom_empty_view"
        app:error_View="@layout/custom_error_view"
        app:loading_View="@layout/custom_loading_view"
        app:noNetwork_View="@layout/custom_no_network_view">

		第二种用法：
        直接写在MultipleStatusView节点内，如下。

  	  <com.charles.multiple_status_view.MultipleStatusView
        android:id="@+id/main_multiplestatusview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:empty_View="@layout/custom_empty_view"
        app:error_View="@layout/custom_error_view"
        app:loading_View="@layout/custom_loading_view"
        app:noNetwork_View="@layout/custom_no_network_view">

         <TextView
            android:id="@+id/content_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:gravity="center"
            android:text="内容视图"
            android:textSize="20sp" />


    </com.charles.multiple_status_view.MultipleStatusView>

</code>

2，使用注意：
	
	对应view的id必须跟下面规定的id一致，

 	<item name="empty_view" type="id"/>
    <item name="error_view" type="id"/>
    <item name="loading_view" type="id"/>
    <item name="content_view" type="id"/>
    <item name="no_network_view" type="id"/>

	例如：emptyView.layout,中根节点的id必须为empyt_view;

3, 设置retryClickListener,注意点击view的id,必须如下：

	<item name="empty_retry_view" type="id"/>
    <item name="error_retry_view" type="id"/>
    <item name="no_network_retry_view" type="id"/>
	
	例如：empytView.layout,中可以点击的view，id必须设置为empty_retry_view;

4,	API
	
	MultipleStatusView.showXX(),
	MultipleStatuesView.setOnRetryClickListener();
5,效果图：
![](http://i.imgur.com/qGDXsJu.gif)


       
         
    

