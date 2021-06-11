package com.yyh.main;


import java.util.Arrays;
import java.util.List;

/**
 * 按照个人理解，题目中说inputItems已经根据itemId排好序，因此只需要遍历inputItems中的每个FeeItem对象，
 *如果该对象尚未加入结果集并且符合条件，则将该对象加入结果集并标记该feedItem已加入结果集,然后从头遍历inputItems
 * 如果不符合，则遍历判断下一个
 * 时间复杂度为O(n*n),空间复杂度为O（n)
 * 因题目没有样例，不知题意是否理解正确
 * 这里使用的是暴力枚举法，另一个思路是把所有对象根据author分组，然后再在每一个小组里选取，不过这里可能得到结果集中id不是有序，不清楚是否符合题意
 */
public class Main {
    public List<FeedItem> reorderFeedItems(List<FeedItem> inputItems){
        boolean[] visited =new boolean[inputItems.size()];
        FeedItem []resArray=new FeedItem[10];//结果集
        int index=0;
        int size=inputItems.size();
        for(int i=0;i<size;){
            if(visited[i]){
                i++;
                continue;
            }
            FeedItem feedItem=inputItems.get(i);
            if(index>0){//防止数组越界
                if(resArray[index-1].getAuthor().equals(feedItem.getAuthor())){// 相同author的FeedItem不能相邻

                    i++;//判断下一个
                }
                else if(index-2<0){//结果集中只有一个值
                    resArray[index]=feedItem;
                    index++;
                    visited[i]=true;//标记
                    i=0;//从头遍历
                }
                else if(!(resArray[index-2].getCategory().equals(feedItem.getCategory())&&
                            resArray[index-1].getCategory().equals(feedItem.getCategory()))){//相同category的FeedItem最多连续显示2条
                    resArray[index]=feedItem;
                    index++;
                    visited[i]=true;//标记
                    i=0;//从头遍历
                }
                else{
                    i++;

                }
            }
            else {//第一个元素
                resArray[index]=feedItem;
                index++;
                visited[i]=true;//标记，从头遍历
                i=0;
            }
            if(index==10){//取得10个则重排完成
                return Arrays.asList(resArray);
            }

        }
        //没有取够十个，追加靠前FeedItem来补足10条
        for(int i=0;i<size;i++){
            if(!visited[i]){
                resArray[index++]=inputItems.get(i);
                if(index==10)
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
