package test;


import com.alibaba.fastjson.JSON;

public class Test {


    public static void main(String[] args) {
        int[] a = new int[]{-1, -3};
        System.out.println(solution(a));
    }

    //快排实现方法
    public static void quickRow(int[] array,int low,int high) {
        int i,j,pivot;
        //结束条件
        if(low >= high) {
            return;
        }
        i = low;
        j = high;
        //选择的节点，这里选择的数组的第一数作为节点
        pivot = array[low];
        while(i<j) {
            //从右往左找比节点小的数，循环结束要么找到了，要么i=j
            while(array[j] >= pivot && i<j) {
                j--;
            }
            //从左往右找比节点大的数，循环结束要么找到了，要么i=j
            while(array[i] <= pivot && i<j) {
                i++;
            }
            //如果i!=j说明都找到了，就交换这两个数
            if(i<j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        //i==j一轮循环结束，交换节点的数和相遇点的数
        array[low] = array[i];
        array[i] = pivot;
        //数组“分两半”,再重复上面的操作
        quickRow(array,low,i-1);
        quickRow(array,i+1,high);
    }

    public static int solution(int[] A) {
        quickRow(A,0,A.length-1);
        System.out.println(JSON.toJSONString(A));

        for(int i=0;i<A.length;i++){
            if(i+1==A.length){
                if(A[i]<0){
                    return 1;
                }
                return A[i]+1;
            }
            if((A[i]+1)==A[i+1] || (A[i])==A[i+1]){
                    continue;
            }else if(A[i+1] <0){
                continue;
            }else{
                return A[i]+1;
            }
        }

        return 0;

    }


}
