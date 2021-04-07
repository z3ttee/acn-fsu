import { Component } from "@angular/core";
import products from "../../api/products/products.json"

@Component({
    selector: "pm-products",
    templateUrl: "./product-list.component.html"
})
export class ProductListComponent {
    pageTitle: string = "Product List";
    imageWidth: number = 50;
    imageMargin: number = 2;
    showImage: boolean = false;
    products: any[] = products;

    public toggleImage(): void {
        this.showImage = !this.showImage;
    }
}