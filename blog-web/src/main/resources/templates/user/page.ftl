<div class="panel panel-default">
    <div class="panel-body text-center" style="font-size: 18px;">
        <ul class="list-inline">

            <!--
            分页思路：

                两种情况：总页数大于5
                        总页数小于5

                       小于等于 5 的情况：
                          1. 页数等于 1 ： 展示 没有更多数据了
                          2. 页数大于 1  并且等于 2： 根据当前页判断展示首页/上一页 还是 尾页/下一页
                          3. 页数大于 2 ： 展示全部页码，根据首页判断展示首页/上一页 还是 尾页/下一页
                       大于 5 的情况：
                          1. 如果当前页 < 3 ：显示 2-5
                          2. 如果当前页 >= 3 并且 <= 5； 显示 （3-1）- （3+2）

            -->
        <#if articlePage.number + 1 == 1>
            <!-- 当页码等于 1 的时候，只显示一个下一页-->
            <li><a href="/page/${articlePage.number + 2}" style="text-decoration: none;font-family: 'Hiragino Sans GB',serif;color: dodgerblue">下一页</a></li>
            <#else>
                <!-- 当前页码 大于等于2的时候，显示首页和上一页-->
                <#if articlePage.number + 1 gte 2 >
                    <li><a style="text-decoration: none;font-family: 'Hiragino Sans GB',serif;color: dodgerblue" href="/">首页</a> </li>
                    <li><a href="/page/${articlePage.number}" style="text-decoration: none;font-family: 'Hiragino Sans GB',serif;color: dodgerblue">上一页</a></li>
                </#if>

                <!-- 当前页码 -2 大于 1 并且 当前页码 + 2 大于 总页数-->
                <#if articlePage.number - 1 gt 1 && articlePage.number + 3 lt articlePage.totalPages>
                    <#list articlePage.number - 1 .. articlePage.number+ 3 as i>
                        <#if i == articlePage.number + 1>
                            <li><span style="font-family: Georgia, serif;color: black">${i}</span></li>
                        <#else>
                            <li><a href="" style="text-decoration: none;font-family: Georgia, serif;color: dodgerblue">${i}</a></li>
                        </#if>
                    </#list>
                <!-- 当前页码 -2 小于 1 -->
                <#elseif articlePage.number - 1 lt 1>
                    <!-- 当前页码 + 2 大于总页数-->
                    <#if articlePage.number + 3 gte articlePage.totalPages>
                        <#list 1 .. articlePage.totalPages as i>
                            <#if i == articlePage.number + 1>
                                <li><span style="font-family: Georgia, serif;color: black">${i}</span></li>
                            <#else>
                                <li><a href="" style="text-decoration: none;font-family: Georgia, serif;color: dodgerblue">${i}</a></li>
                            </#if>
                        </#list>
                    <!-- 当前页码 + 2 小于总页数-->
                    <#else>
                        <#list 1 .. articlePage.number+ 3 as i>
                            <#if i == articlePage.number + 1>
                                <li><span style="font-family: Georgia, serif;color: black">${i}</span></li>
                            <#else>
                                <li><a href="" style="text-decoration: none;font-family: Georgia, serif;color: dodgerblue">${i}</a></li>
                            </#if>
                        </#list>
                    </#if>
                <!-- 当前页面 + 2 大于总页数-->
                <#elseif articlePage.number + 3 gt articlePage.totalPages>
                    <#if articlePage.number - 2 lte 0>
                        <#list 1 .. articlePage.totalPages as i>
                            <#if i == articlePage.number + 1>
                                <li><span style="font-family: Georgia, serif;color: black">${i}</span></li>
                            <#else>
                                <li><a href="" style="text-decoration: none;font-family: Georgia, serif;color: dodgerblue">${i}</a></li>
                            </#if>
                        </#list>
                    <#else>
                        <#list articlePage.number - 2 .. articlePage.totalPages as i>
                            <#if i == articlePage.number + 1>
                                <li><span style="font-family: Georgia, serif;color: black">${i}</span></li>
                            <#else>
                                <li><a href="" style="text-decoration: none;font-family: Georgia, serif;color: dodgerblue">${i}</a></li>
                            </#if>
                        </#list>
                    </#if>

                <!-- 当前页面 - 2 小于 1 并且当前页面 + 2 大于总页数 -->
                <#elseif articlePage.number - 1 lt 1 && articlePage.number + 3 gt articlePage.totalPages>
                    <#list 1 .. articlePage.totalPages as i>
                        <#if i == articlePage.number + 1>
                            <li><span style="font-family: Georgia, serif;color: black">${i}</span></li>
                        <#else>
                            <li><a href="" style="text-decoration: none;font-family: Georgia, serif;color: dodgerblue">${i}</a></li>
                        </#if>
                    </#list>
                </#if>

                <!--当前页码小于等于 总页数的时候，显示尾页和下一页 -->
                <#if (articlePage.number +1) lt (articlePage.totalPages)>
                    <li><a href="/page/${articlePage.number + 2}" style="text-decoration: none;font-family: 'Hiragino Sans GB',serif;color: dodgerblue">下一页</a></li>
                    <li><a href="/page/${articlePage.totalPages}" style="text-decoration: none;font-family: 'Hiragino Sans GB',serif;color: dodgerblue">尾页</a></li>
                </#if>
        </#if>
        </ul>
    </div>
</div>