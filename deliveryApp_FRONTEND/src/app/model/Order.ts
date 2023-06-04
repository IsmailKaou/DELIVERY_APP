import { Product } from './Product';

export interface Order {
  orderId: number;
  dateCreated: string;
  expectedArrivedDate: string;
  orderStatus: string;
  totalAmount: number;
  billingAddress: string;
  deliveryMode: string;
  products: Product[];
}
