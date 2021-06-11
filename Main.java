package com.yyh.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 按照个人理解，题目中说inputItems已经根据itemId排好序，因此只需要遍历inputItems中的每个FeeItem对象，
 *依次判断符不符合条件而选择是否加进结果集或这其他集中，如果遍历完成后结果集中数量小于10，再从其他集中选取元素添加到结果集中即可
 */
public class Main {
    public List<FeedItem> reorderFeedItems(List<FeedItem> inputItems){
        List<FeedItem>res=new ArrayList<>();
        FeedItem []resArray=new FeedItem[10];//结果集
        List<FeedItem>otherArray=new ArrayList<>();//其他集
        int index=0;
        for(FeedItem feedItem:inputItems){
            if(index>0){//防止数组越界
                if(resArray[index-1].getAuthor().equals(feedItem.getAuthor())){// 相同author的FeedItem不能相邻
                    otherArray.add(feedItem);

                }
                else if(index-2<0){//结果集中只有一个值
                    resArray[index]=feedItem;
                    index++;
                }
                else if(!(resArray[index-2].getCategory().equals(feedItem.getCategory())&&
                            resArray[index-1].getCategory().equals(feedItem.getCategory()))){//相同category的FeedItem最多连续显示2条
                    resArray[index]=feedItem;
                    index++;
                }
                else{
                    otherArray.add(feedItem);

                }
            }
            else {//第一个元素
                resArray[index]=feedItem;
                index++;
            }
            if(index==10){//取得10个则重排完成
                return Arrays.asList(resArray);
            }

        }
        //没有取够十个，追加靠前FeedItem 来补足1 0 条
        for(FeedItem feedItem:otherArray){
            resArray[index++]=feedItem;
            if(index==10){
                break;
            }
        }
        return Arrays.asList(resArray);
    }
}
class  FeedItem {

    private int itemId;

    private String title;

    private String[] pics;

    private String author;

    private String category;

    public int getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String[] getPics() {
        return pics;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

}
