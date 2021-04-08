import { Component, OnInit } from "@angular/core";
import products from "../../api/products/products.json"
import { IProduct } from "./product";

@Component({
    selector: "pm-products",
    templateUrl: "./product-list.component.html",
    styleUrls: ["./product-list.component.css"]
})
export class ProductListComponent implements OnInit {
    
    pageTitle: string = "Product List";
    imageWidth: number = 50;
    imageMargin: number = 2;
    showImage: boolean = false;

    products: IProduct[] = products;
    filteredProducts: IProduct[] = [];

    private _listFilter: string = "";

    get listFilter(): string {
        return this._listFilter;
    }
    set listFilter(value: string) {
        this._listFilter = value;
        this.filteredProducts = this.performFilter(value);
    }

    public toggleImage(): void {
        this.showImage = !this.showImage;
    }

    public performFilter(filterBy: string): IProduct[] {
        filterBy = filterBy.toLowerCase();
        return this.products.filter((product: IProduct) => product.productName.toLocaleLowerCase().includes(filterBy));
    }

    public ngOnInit(): void {
        this.listFilter = ""
    }
}