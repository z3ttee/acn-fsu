import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  pageTitle: string = "Product Detail";

  constructor(private activatedRoute: ActivatedRoute) { }

  public ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get("id")
  }

}
