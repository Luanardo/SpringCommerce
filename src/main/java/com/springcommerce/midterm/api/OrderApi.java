package com.springcommerce.midterm.api;

import com.springcommerce.midterm.dto.OrderDTO;
import com.springcommerce.midterm.dto.OrderDetailDTO;
import com.springcommerce.midterm.dto.ProductDTO;
import com.springcommerce.midterm.model.Order;
import com.springcommerce.midterm.service.IOrderService;
import com.springcommerce.midterm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderApi {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<String> createOrder(
            @ModelAttribute OrderDTO orderDTO,
            @RequestParam("detail") List<String> orderDetailString
    ) {
        for (String orderDetail: orderDetailString) {
            String[] detail = orderDetail.split("-");
            ProductDTO productDTO = productService.getOne(Long.parseLong(detail[0]));
            if (productDTO.getRemainingQuantity() < Integer.parseInt(detail[1])) {
                return new ResponseEntity<>(productDTO.getName()+" đã hết hàng", HttpStatus.BAD_REQUEST);
            }
        }
        OrderDTO savedOrderDTO = orderService.save(orderDTO);
        for (String orderDetail: orderDetailString) {
            String[] detail = orderDetail.split("-");
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setOrder(savedOrderDTO.getId());
            orderDetailDTO.setProduct(Long.parseLong(detail[0]));

            int quantity = Integer.parseInt(detail[1]);

            orderDetailDTO.setQuantity(quantity);
            orderService.saveDetail(orderDetailDTO);
            ProductDTO productDTO = productService.getOne(Long.parseLong(detail[0]));

            int remainingQuantity = productDTO.getRemainingQuantity();
            productDTO.setRemainingQuantity(remainingQuantity - quantity);
            productService.save(productDTO);
        }
        return new ResponseEntity<>("Tạo đơn hàng thành công", HttpStatus.CREATED);
    }

    @PutMapping("/delivering/{id}")
    public ResponseEntity<String> changeStatusToDelivering(@PathVariable("id") Long id) {
        orderService.changeStatus(id, "Đang giao hàng");
        return new ResponseEntity<>("Đã chuyển trạng thái đơn hàng sang 'Đang giao hàng'", HttpStatus.OK);
    }

    @PutMapping("/delivered/{id}")
    public ResponseEntity<String> changeStatusToDelivered(@PathVariable("id") Long id) {
        orderService.changeStatus(id, "Đã giao hàng");
        return new ResponseEntity<>("Đã chuyển trạng thái đơn hàng sang 'Đã giao hàng'", HttpStatus.OK);
    }

    @PutMapping("/canceled/{id}")
    public ResponseEntity<String> changeStatusToCanceled(@PathVariable("id") Long id) {
        orderService.changeStatus(id, "Đã hủy");
        return new ResponseEntity<>("Đã chuyển trạng thái đơn hàng sang 'Đã hủy'", HttpStatus.OK);
    }
}
