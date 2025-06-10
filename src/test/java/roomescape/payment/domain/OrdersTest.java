package roomescape.payment.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class OrdersTest {

    @Test
    void 주문_대기_상태여야만_결제_성공이_가능하다() {
        // given
        String paymentKey = "paymentKey";
        String orderId = "orderId";
        Orders orders = new Orders(paymentKey, orderId, OrderStatus.PENDING);
        // when
        orders.success();

        // then
        assertThat(orders.getStatus()).isEqualTo(OrderStatus.SUCCESS);
    }

    @Test
    void 주문_대기_상태여야만_결제_실패가_가능하다() {
        // given
        String paymentKey = "paymentKey";
        String orderId = "orderId";
        Orders orders = new Orders(paymentKey, orderId, OrderStatus.PENDING);
        // when
        orders.failed();

        // then
        assertThat(orders.getStatus()).isEqualTo(OrderStatus.FAILED);
    }

    @Test
    void 결제_대기_상태가_아니면_성공_변경_시_예외가_발생한다() {
        String paymentKey = "paymentKey";
        String orderId = "orderId";
        Orders orders = new Orders(paymentKey, orderId, OrderStatus.SUCCESS);

        assertThatThrownBy(orders::success).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 결제_대기_상태가_아니면_실패_변경_시_예외가_발생한다() {
        String paymentKey = "paymentKey";
        String orderId = "orderId";
        Orders orders = new Orders(paymentKey, orderId, OrderStatus.SUCCESS);

        assertThatThrownBy(orders::failed).isInstanceOf(IllegalStateException.class);
    }
}
