def solution(arr):
    qq=[]
    count=0
    for i in arr:
        if count==0:
            qq.append(i)
            count+=1
        else:
            if(qq[count-1]!=i):
                qq.append(i)
                count+=1
    return qq