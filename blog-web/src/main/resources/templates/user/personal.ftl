<div class="panel panel-default">
    <div class="panel-body" style="font-size: 16px;font-family: 'Hiragino Sans GB',serif;">
        <p>不荒原名李启文，生于 1993-9-24，24 岁，英文 ID：selfassu。</p>
        <p>- 于 2016 年 3 月毕业于
            <a href="http://www.hbmykjxy.cn/"  target="_blank" style="font-family: 'Hiragino Sans GB',serif;color: dodgerblue">
                 湖北民族学院科技学院
            </a>。
        </p>
        <p>- 现居住于<a target="_blank" href="https://baike.baidu.com/item/%E5%8D%97%E5%B1%B1%E5%8C%BA/21023?fr=aladdin&fromid=17582705&fromtitle=%E6%B7%B1%E5%9C%B3%E5%8D%97%E5%B1%B1%E5%8C%BA" style="text-decoration: none;color: dodgerblue; "> 广东 - 深圳 - 南山区</a></p>
        <p>- 自毕业后，一直在现公司担任「Android 开发工程师」，虽然是从事的是「Android 开发」，但是我对后台也有这浓厚的兴趣，因此在
            公司也会做一些后台相关的项目，下一份工作期待能够成为一名真正的后端开发工程师。</p>
        <p>- 在 <a href="https://github.com/selfassu"
                  style="text-decoration: none;color: dodgerblue;" target="_blank">Github</a> 上积极参与开源社区。</p>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <ul class="list-inline">
        <#list tagList as tag>
            <li>
                <a href="" style="font-size: 16px;font-family: 'Hiragino Sans GB',serif;color: dodgerblue;">
                ${tag.name}
                </a>
            </li>
        </#list>
        </ul>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-body">
        <ul style="font-size: 16px;font-family: 'Hiragino Sans GB',serif;line-height: 28px; list-style: none">
            <#list archives?keys as key>
            <li><a href="" style="color: dodgerblue; text-decoration: none;">${key}</a> (${archives[key]?size}) </li>
            </#list>
        </ul>
    </div>
</div>