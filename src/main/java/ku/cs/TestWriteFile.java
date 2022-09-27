//package ku.cs;
//
//import ku.cs.shop.models.MemberCard;
//import ku.cs.shop.models.MemberCardList;
//import ku.cs.shop.services.DataSource;
//import ku.cs.shop.services.MemberCardListFileDataSource;
//
//public class TestWriteFile {
//    public static void main(String[] args){
//        MemberCardList list = new MemberCardList();
//        list.addCard(new MemberCard("Name Joja","6969696911"));
//        list.addCard(new MemberCard("Name Cola","6969696912",69));
//        list.addCard(new MemberCard("Name Hoha","6969696913"));
//        list.addCard(new MemberCard("Name Lola","6969696914",96));
//
//        DataSource<MemberCardList> dataSource = new MemberCardListFileDataSource("data","test.csv");
//        dataSource.writeData(list);
//    }
//}
