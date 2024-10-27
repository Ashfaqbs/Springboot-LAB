## Simple examples to illustrate `thenApply()` and `thenAccept()`.

### 1. `thenApply()`
- **Purpose**: Used to transform the result of a `CompletableFuture` once it completes.
- **Returns**: A new `CompletableFuture` with the transformed result.

#### Example:
Imagine we have a `CompletableFuture` that fetches a product price, and we want to add tax to it:

```java
CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(() -> {
    return 100.0; // Original product price
});

CompletableFuture<Double> priceWithTaxFuture = priceFuture.thenApply(price -> price * 1.18); // Add 18% tax

priceWithTaxFuture.thenAccept(finalPrice -> System.out.println("Final Price with Tax: " + finalPrice));
```

**Explanation**:
- `priceFuture` fetches the initial product price asynchronously.
- `thenApply(price -> price * 1.18)` applies the 18% tax to the price.
- `priceWithTaxFuture` now contains the result of that transformation, which we can use further or print directly.

### 2. `thenAccept()`
- **Purpose**: Used to consume the result of a `CompletableFuture` without transforming it.
- **Returns**: A `CompletableFuture<Void>` since it does not return a new result, just processes the existing one.

#### Example:
Using `thenAccept()` to just display the price after fetching:

```java
CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(() -> {
    return 100.0; // Original product price
});

priceFuture.thenAccept(price -> System.out.println("Original Price: " + price));
```

**Explanation**:
- `thenAccept(price -> System.out.println("Original Price: " + price))` consumes the price by printing it. No further transformation or return is needed.

### Key Difference
- **`thenApply()`**: Used to transform the result and returns a `CompletableFuture` with the transformed value.
- **`thenAccept()`**: Used to perform an action on the result (like printing or logging) without transforming or returning a new value.
