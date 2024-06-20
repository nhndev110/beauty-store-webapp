/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `nhndev_beauty` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `nhndev_beauty`;

CREATE TABLE IF NOT EXISTS `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `user_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cart_ibfk_1` (`user_id`),
  KEY `cart_ibfk_2` (`product_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE IF NOT EXISTS `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `thumbnail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `categories` (`id`, `name`, `thumbnail`) VALUES
	(1, 'Nước Hoa', 'nuoc-hoa.jpg'),
	(4, 'Trang Điểm Mặt', 'trang-diem-mat.jpg'),
	(6, 'Sữa Rửa Mặt', 'sua-rua-mat.jpg'),
	(7, 'Sữa Tắm', 'sua-tam.jpg');

CREATE TABLE IF NOT EXISTS `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_total` double NOT NULL DEFAULT (0),
  `order_date` timestamp NOT NULL DEFAULT (now()),
  `receiver_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `receiver_phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `receiver_address` text COLLATE utf8mb4_general_ci NOT NULL,
  `order_status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_orders_users` (`user_id`),
  CONSTRAINT `FK_orders_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE IF NOT EXISTS `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `image` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `status` bit(1) NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`) USING BTREE,
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `products` (`id`, `name`, `image`, `description`, `price`, `quantity`, `status`, `category_id`) VALUES
	(73, 'Son Kem GEGE BEAR tông màu hổ phách chống dính lâu trôi 3g 6 màu tùy chọn', 'facebook-dynamic-nuoc-hoa-nu-laura-anne-diamond-pour-femme-edp-45ml_1703513651001.jpg', '1. Nước Hoa Nữ Laura Anne Diamond Pour Femme EDP 45ml (Hồng) \r\nDIAMOND pour FEMME là một mùi hương hoa lấp lánh, sự ngọt ngào của Hoa hồng được làm sáng bởi sắc thái tươi mát của lá violet và được tăng thêm sự cuốn hút bởi xạ hương. Ở nốt hương đầu, Diamond Femme đã mang đến vẻ đẹp tươi mát của lá xanh, của những loại trái cây nước thanh khiết và trộn lẫn một ít sự ngọt ngào của táo đỏ. Tiếp nối đó, tinh chất hoa hồng kết hợp với lá violet và hoa linh lan mang đến vẻ đẹp chín chắn của người phụ nữ quyến rũ, tự tin. Ở cô ấy luôn tràn đầy sự thu hút và lôi cuốn bởi vẻ đẹp ngọt ngào, đầy nữ tính. Tầng hương cuối dường như là "lớp đệm" để nốt hương hoa chủ đạo có thể tỏa sáng hơn và lưu lại lâu hơn trên da. Một chút nhấn nhá với gỗ đàn hương trầm ấm hòa quyện với vẻ gợi cảm, thu hút của xạ hương trắng. Tất cả tạo nên một sáng tác hoàn hảo, cuồn hút và rất hiện đại.\r\n\r\n2. Nước Hoa Nữ Laura Anne Diamond Pour Femme 45ml (Trắng)\r\nTrải nghiệm một hương thơm thể hiện sức hút nữ tính, đầy tinh tế, tươi vui và thanh lịch với nốt hương chính là hoa diên vĩ kết hợp với hoa nhài cùng quả chanh vàng, một chút xạ hương, cây hoắc hương, hương Vani và cỏ hương bài, Diamond là đặc trưng của sự thanh lịch và tinh tế, với sức mạnh lưu hương tuyệt vời. Mẫu nước hoa tuyệt vời trên cả kỳ vọng này phù hợp sử dụng trong bất cứ thời gian nào, dù là văn phòng làm việc hay những buổi hẹn hò. Hãy khoác lên mình bộ quần áo ưa thích, xịt thêm vài giọt Diamond pour Femme, bạn sẽ trông thật tuyệt vời và thu hút mọi ánh nhìn khi dạo phố hay trong những buổi tối hẹn hò cùng chàng.', 11.31, 10, b'1', 1),
	(74, 'Nước Hoa Vùng Kín Foellie Hương Hoa Hồng Mạnh Mẽ 5ml Inner Perfume #Eau De Bijou', 'nuoc-hoa-vung-kin-foellie-huong-hoa-hong-manh-me-5ml_1703515313948.jpg', 'Nước Hoa Vùng Kín Foellie 5ml là sản phẩm nước hoa vùng kín đến từ thương hiệu Foellie của Hàn Quốc, có khả năng loại bỏ mùi hôi khó chịu và tạo mùi hương tự nhiên. Chiết xuất từ thành phần thiên nhiên an toàn, lành tính cho da nhạy cảm. Sản phẩm đã được cấp giấy chứng nhận hiệu quả và an toàn sử dụng tại Hàn Quốc và Bộ Y Tế Việt Nam.', 5.74, 20, b'1', 1),
	(75, 'Nước Hoa Nữ Diamond Femme Pink 45ml (Hồng) Eau De Parfum', 'nuoc-hoa-nu-laura-anne-diamond-pour-femme-edp-45ml_1703516116636.jpg', 'Nước Hoa Laura Anne Diamond Cho Nữ 45ml là sản phẩm nước hoa eau de parfum cho nữ đến từ thương hiệu Laura Anne được sản xuất trực tiếp tại Việt Nam với nguồn nguyên liệu nhập khẩu theo công nghệ Singapore. Thiết kế cổ điển, thanh lịch sẽ giúp bạn lưu lại trên người hương thơm đầy quyến rũ, đầy mê hoặc, giúp bạn tự tin hơn khi giao tiếp. Nằm trong bộ sưu tập Nước Hoa Diamond được ra đời từ nhà tạo hương hàng đầu thế giới Firmenich là chất xúc tác giúp điểm tô thần thái của bạn dù ở bất kì nơi đâu.', 11.31, 100, b'1', 1),
	(76, 'Nước Hoa Nam Diamond Homme 45ml (Đen) Eau De Parfum', 'nuoc-hoa-nam-laura-anne-diamond-pour-homme-45ml_1703516265740.jpg', 'Nước Hoa Laura Anne Diamond Cho Nam 45ml là sản phẩm nước hoa eau de parfum cho nam đến từ thương hiệu Laura Anne được sản xuất trực tiếp tại Việt Nam với nguồn nguyên liệu nhập khẩu theo công nghệ Singapore. Thiết kế cổ điển, thanh lịch sẽ giúp bạn lưu lại trên người hương thơm đầy quyến rũ, đầy mê hoặc, giúp bạn tự tin hơn khi giao tiếp. Nằm trong bộ sưu tập Nước Hoa Diamond được ra đời từ nhà tạo hương hàng đầu thế giới Firmenich là chất xúc tác giúp điểm tô thần thái của bạn dù ở bất kì nơi đâu.', 14.36, 125, b'1', 1),
	(77, 'Phấn Nước Laneige Cho Lớp Nền Mịn Lì 24H #21N 15g Neo Cushion Matte #21N', 'phan-nuoc-laneige-che-phu-min-li-24h-tong-mau-21n-15g_1703516428956.jpg', 'Phấn Nước Laneige Che Phủ Mịn Lì 24h là dòng phấn nước cushion đến từ thương hiệu mỹ phẩm cao cấp Laneige của Hàn Quốc, tạo lớp nền mịn lì, che phủ tối ưu các khuyết điểm, lỗ chân lông to và vùng da không đều màu suốt 24 giờ. Sản phẩm tạo hiệu ứng bán mờ không gây bóng nhờn cả ngày dài giúp bạn tự tin với vẻ ngoài rạng ngời.', 21.87, 230, b'1', 4),
	(78, 'Phấn Phủ innisfree Kiềm Dầu Dạng Bột Khoáng 5g (Mới 2023) No-Sebum Mineral Powder', 'phan-phu-innisfree-kiem-dau-dang-bot-khoang-5g_1703516526911.png', 'Phấn Phủ Kiềm Dầu Dạng Bột Khoáng innisfree No Sebum Mineral Powder là sản phẩm phấn phủ đến từ thương hiệu mỹ phẩm innisfree của Hàn Quốc với chiết xuất từ bạc hà và khoáng chất tự nhiên Jeju, kiềm dầu đồng thời tạo độ che phủ tự nhiên cho lớp nền khô thoáng. ', 5.57, 200, b'1', 4),
	(79, 'Sữa Rửa Mặt Cetaphil Dịu Lành Cho Da Nhạy Cảm 500ml (Mới) Gentle Skin Cleanser (New)', 'sua-rua-mat-cetaphil-diu-nhe-khong-xa-phong-500ml-moi_1703516956918.png', 'Sữa Rửa Mặt Cetaphil Gentle Skin Cleanser phiên bản mới ra mắt năm 2022 từ thương hiệu Cetaphil với công thức khoa học mới cho làn da nhạy cảm, giúp làm sạch da, loại bỏ bụi bẩn, phù hợp cho mọi loại da, không làm khô da và duy trì hàng rào bảo vệ da suốt ngày dài.\r\n\r\nĐược chứng minh lâm sàng có khả năng làm sạch sâu, loại bỏ hoàn toàn bụi bẩn, và tạp chất trên da một cách dịu nhẹ nhưng vẫn duy trì độ ẩm tự nhiên để bảo vệ da khỏi tình trạng khô ráp, Sữa Rửa Mặt Cetaphil Mới với công thức lành tính không gây kích ứng sẽ an toàn cho mọi loại da, kể cả da nhạy cảm.', 13.7, 150, b'1', 6),
	(80, 'Gel Rửa Mặt Cosrx Tràm Trà, 0.5% BHA Có Độ pH Thấp 150ml Low pH Good Morning Gel Cleanser', 'gel-rua-mat-cosrx-tram-tra-0-5-bha-co-do-ph-thap-150ml_1703517096448.jpg', 'Gel Rửa Mặt Cosrx Tràm Trà, 0.5% BHA Có Độ pH Thấp là dòng sữa rửa mặt đến từ thương hiệu mỹ phẩm Cosrx của Hàn Quốc, với độ pH lý tưởng 4.5 - 5.5 sản phẩm an toàn và dịu nhẹ trên mọi làn da ngay cả làn da nhạy cảm và da mụn. Gel rửa mặt chứa 0,5% BHA tự nhiên và chiết xuất tràm trà làm sạch sâu lỗ chân lông, hỗ trợ kháng khuẩn, làm sạch mụn đồng thời tẩy da chết nhẹ nhàng.', 4.87, 300, b'1', 6),
	(81, 'Gel Rửa Mặt SVR Không Chứa Xà Phòng Cho Da Dầu 400ml Sebiaclear Gel Moussant', 'gel-rua-mat-svr-khong-chua-xa-phong-cho-da-dau-400ml_1703517188829.png', 'Gel Rửa Mặt SVR Sebiaclear Gel Moussant là sản phẩm sữa rửa mặt dành cho làn da dầu mụn đến từ thương hiệu dược mỹ phẩm SVR của Pháp, với công thức không chứa xà phòng giúp làm sạch, nhẹ nhàng làm thông thoáng làn da. Khả năng tạo bọt mịn giúp loại trừ các chất bẩn và lượng bã nhờn dư thừa mà không làm khô da. Có thể rửa sạch dễ dàng, mang lại một làn da sạch, tươi mát và khô thoáng.', 16.47, 123, b'1', 6),
	(82, 'Gel Rửa Mặt La Roche-Posay Dành Cho Da Dầu, Nhạy Cảm 400ml', 'gel-rua-mat-la-roche-posay-cho-da-dau-nhay-cam-400ml_1703517274718.jpg', 'Gel Rửa Mặt La Roche-Posay Effaclar Purifying Foaming Gel For Oily Sensitive Skin là dòng sản phẩm sữa rửa mặt chuyên biệt dành cho làn da dầu, mụn, nhạy cảm đến từ thương hiệu dược mỹ phẩm La Roche-Posay nổi tiếng của Pháp, với kết cấu dạng gel tạo bọt nhẹ nhàng giúp loại bỏ bụi bẩn, tạp chất và bã nhờn dư thừa trên da hiệu quả, mang đến làn da sạch mịn, thoáng nhẹ và tươi mát. Công thức sản phẩm an toàn, lành tính, giảm thiểu tình trạng kích ứng đối với làn da nhạy cảm.', 19.65, 235, b'1', 6),
	(83, 'Sữa Tắm Hatomugi Dưỡng Ẩm, Sáng Da Chiết Xuất Ý Dĩ 800ml Reihaku Hatomugi Body Soap', 'sua-tam-hatomugi-duong-sang-da-800ml_1703517507020.jpg', 'Sữa Tắm Hatomugi Dưỡng Ẩm, Sáng Da Chiết Xuất Ý Dĩ 800ml đến từ thương hiệu HATOMUGI thường xuyên lọt vào top sữa tắm được yêu thích của các tạp chí làm đẹp tại Nhật, với thành phần chiết xuất hạt ý dĩ giàu dưỡng chất, không chỉ giúp làm sạch da mà còn dưỡng ẩm cho da, nuôi dưỡng cho làn da sáng mịn từ bên trong.\r\n\r\nHiện sản phẩm Sữa Tắm Hatomugi Reihaku Hatomugi Body Soap 800ml đã có mặt tại Hasaki.', 3.26, 130, b'1', 7),
	(84, 'Kem Tắm Secret Key Dưỡng Sáng Da Mặt Và Cơ Thể 200g Snow White Milky Pack', 'kem-tam-secret-key-snow-white-duong-sang-da-200g_1703517582319.png', 'Secret Key Snow White Milky Pack là sản phẩm sữa tắm ủ sáng da đến từ thương hiệu mỹ phẩm Secret Key của Hàn Quốc, chứa có các thành phần làm sáng an toàn cho da như Centella Asiatica, dầu hoa trà, Glyoerophosphoric axit, dầu Mầm lúa mì, B-glucan, Sodium Hyaluronate. Ngoài ra sản phẩm còn chứa Niacinamide giúp da sáng mịn một cách tự nhiên và đều màu đồng thời cấp nước và dưỡng ẩm thường xuyên. Sản phẩm làm sáng cho cả cơ thể và mặt. ', 8.91, 365, b'1', 7),
	(85, 'Gel Rửa Mặt & Tắm La Roche-Posay Làm Sạch & Giảm Mụn 400ml', 'gel-rua-mat-tam-la-roche-posay-lam-sach-giam-mun-400ml_1703517714058.jpg', 'Gel Rửa Mặt & Tắm La Roche-Posay Effaclar Micro-Peeling Purifying Gel là sản phẩm rửa mặt và sữa tắm 2 trong 1 mới nhất vừa được ra mắt từ thương hiệu dược mỹ phẩm La Roche-Posay, thuộc dòng Effaclar chăm sóc da dầu mụn, với tác động kép - hiệu quả 2 trong 1 dùng được cho mặt và toàn thân, không chỉ giúp làm sạch dịu nhẹ mà còn hỗ trợ giảm mụn, vết thâm và ngăn ngừa mụn tái phát.', 19.77, 250, b'1', 7),
	(86, 'test1', 'ppp_1703577558775.jpg', 'sss', 30.2, 12, b'1', 4);

CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `password` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `auth_token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `users` (`id`, `name`, `email`, `phone`, `address`, `password`, `role`, `auth_token`, `remember_token`) VALUES
	(1, 'Lê Văn Tèo', 'lvteo@gmail.com', '0913333333', '', '7978723e0d5d8372ada8e953675185c9', '', '2fabb207158e411fffdc0169a0570d93', NULL),
	(2, 'Trần Thị Út', 'ttut@gmail.com', '0903333333', '', '7978723e0d5d8372ada8e953675185c9', 'admin', NULL, NULL),
	(3, 'Nguyễn Hoàng Nhân', 'nguyenhoangnhannn93@gmail.com', '0868912150', '', '7978723e0d5d8372ada8e953675185c9', '', NULL, NULL),
	(4, 'Hoàng Thế Lập', 'lap@gmail.com', '0868912151', '', 'e10adc3949ba59abbe56e057f20f883e', '', NULL, NULL),
	(5, 'Nhan Nguyen Hoang', 'nhan@gmail.com', '0868912152', '', 'bb4e31f2d20f8e7f88e2b8459263657f', '', NULL, NULL),
	(7, 'Nguyễn Quốc Huy', 'huy@gmail.com', '0868912156', '', '202cb962ac59075b964b07152d234b70', '', NULL, NULL),
	(14, 'Nguyen Hoang Nhan', 'nhan123@gmail.com', '0868745454', '', '25d55ad283aa400af464c76d713c07ad', '', NULL, NULL),
	(15, 'Nguyen Hoang Nhan', 'oke@gmail.com', '0233564789', '', '25d55ad283aa400af464c76d713c07ad', '', NULL, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
