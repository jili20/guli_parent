import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;
/**
 * @author bing  @create 2020/12/7-9:06 下午
 */
public class TestVod {

    public static void main(String[] args) throws Exception {
        String accessKeyId = "LTAI4GEaXJ6m9Spb1TGfXjYY";
        String accessKeySecret = "VTtoHccVGyYop8xjNFLs0fPZc2ccbR";

        String title = "6 - What If I Want to Move Faster - upload by sdk";   //上传之后文件名称
        String fileName = "/Users/bing/Movies/guliVideo/6 - What If I Want to Move Faster.mp4";  //本地文件路径和名称
        //上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }

    }

    // 根据视频id获取视频播放凭证
    public static void getPlayAuth() throws Exception {
        // 根据视频id获取视频播放凭证
        String accessKeyId = "LTAI4GEaXJ6m9Spb1TGfXjYY";
        String accessKeySecret = "VTtoHccVGyYop8xjNFLs0fPZc2ccbR";

        DefaultAcsClient client = InitObject.initVodClient(accessKeyId, accessKeySecret);

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        // 向 request 对象里面设置视频 id
        request.setVideoId("08a56bf6a52e4ec9b3556e5ecdf54b04");

        response = client.getAcsResponse(request);
        System.out.println("playAuth:" + response.getPlayAuth());
    }

    // 根据视频id获取视频播放地址
    public static void getPlayUrl() throws Exception {
        String accessKeyId = "LTAI4GEaXJ6m9Spb1TGfXjYY";
        String accessKeySecret = "VTtoHccVGyYop8xjNFLs0fPZc2ccbR";

        // 1.根据视频 id 获取视频播放地址
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient(accessKeyId, accessKeySecret);

        // 创建获取视频地址 request 和 response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        // 向 request 对象里面设置视频 id
        request.setVideoId("08a56bf6a52e4ec9b3556e5ecdf54b04");

        // 调用初始化对象里面的方法，传递 request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.println("PlayInfo.PlayURL =" + playInfo.getPlayURL() + "\n");
        }
        // Base 信息
        System.out.println("VideoBase.Title=" + response.getVideoBase().getTitle() + "\n");

        // 控制台输出：
        // PlayInfo.PlayURL =https://outin-246c26b2bdb111eaaba100163e1a3b4a.oss-cn-shanghai.aliyuncs.com/sv/1891bb40-1763d549b4b/1891bb40-1763d549b4b.mp4?Expires=1607351042&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=4DVt1EJkQf5G3LtpNPI0dn2S9pc%3D
        // VideoBase.Title=6 - What If I Want to Move Faster.mp4
    }

}
