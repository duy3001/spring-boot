package com.project.shopapp.services.orderdetails;

import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.models.Product;
import com.project.shopapp.repositories.OrderDetailRepository;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.services.orderdetails.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        Order order = orderRepository.findById(orderDetailDTO.getOrderID()).orElseThrow(
                () -> new DataNotFoundException("Cannot find order with id = " + orderDetailDTO.getOrderID()));
        Product product = productRepository.findById(orderDetailDTO.getProductID()).orElseThrow(
                () -> new DataNotFoundException("Cannot find product with id = " + orderDetailDTO.getProductID()));
        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .price(orderDetailDTO.getPrice())
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .color(orderDetailDTO.getColor())
                .build();
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetail(Long id) throws DataNotFoundException {
        return orderDetailRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException("Cannot find OrderDetail with id: " + id));
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO)
            throws DataNotFoundException {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order detail with id = " + id));
        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderID())
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id = " + orderDetailDTO.getOrderID()));
        Product existingProduct = productRepository.findById(orderDetailDTO.getProductID())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find product with id = " + orderDetailDTO.getProductID()));

        existingOrderDetail.setPrice(orderDetailDTO.getPrice());
        existingOrderDetail.setColor(orderDetailDTO.getColor());
        existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
        existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        existingOrderDetail.setOrder(existingOrder);
        existingOrderDetail.setProduct(existingProduct);
        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
