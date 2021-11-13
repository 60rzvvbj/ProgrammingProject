import commom.factory.ServiceFactory;
import service.FriendRequestService;
import service.PairingRequestService;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = ServiceFactory.getUserService();
        System.out.println(userService.queryUserByStudentNumber("191543110"));
        System.out.println(userService.queryFriendList("191543110", ""));
        System.out.println(userService.queryUser(""));
        userService.login("191543110", "1234");
        System.out.println("===========================");

        FriendRequestService friendRequestService = ServiceFactory.getFriendRequestService();
        System.out.println(friendRequestService.queryRequest("191543110"));
        System.out.println("===========================");

        PairingRequestService pairingRequestService = ServiceFactory.getPairingRequestService();
        System.out.println(pairingRequestService.queryPairingRequest());
    }
}
