[![Pixaven](media/readme-header.png "Pixaven: GPU-powered Image Processing Platform")](https://www.pixaven.com)

<p align="center">
Pixaven is a modern, GPU-powered image processing API.<br>We transform, enhance, adjust, crop, stylize, filter and watermark your images with blazing speed.
</p>

---
<p align="center">
<strong>The official Java integration for the Pixaven API.</strong><br>
<br>
<img src="https://img.shields.io/maven-central/v/pixaven/pixaven?style=flat&color=success"/>
<img src="https://img.shields.io/snyk/vulnerabilities/github/pixaven/pixaven-java?style=flat&color=success"/>
<img src="https://img.shields.io/github/issues-raw/pixaven/pixaven-java?style=flat&color=success"/>
<img src="https://img.shields.io/twitter/follow/pixaven?label=Follow%20Us&style=flat&color=success&logo=twitter"/>
</p>

---

### Documentation
See the [Pixaven API docs](https://docs.pixaven.com/).

### Quick examples
Pixaven API enables you to provide your images for processing in two ways - by uploading them directly to the API ([Image Upload](https://docs.pixaven.com/requests/image-upload)) or by providing a publicly available image URL ([Image Fetch](https://docs.pixaven.com/requests/image-fetch)).

You may also choose your preferred [response method](https://docs.pixaven.com/introduction#choosing-response-method-and-format) on a per-request basis. By default, the Pixaven API will return a [JSON response](https://docs.pixaven.com/responses/json-response-format) with rich metadata pertaining to input and output images. Alternatively, you can use [binary responses](https://docs.pixaven.com/responses/binary-responses). When enabled, the API will respond with a full binary representation of the resulting (output) image. This Java integration exposes two convenience methods for interacting with binary responses: `toFile()` and `toBuffer()`.

#### Image upload
Here is a quick example of uploading a local file for processing. It calls `toJSON()` at a final step and instructs the API to return a JSON response.

```java
package com.pixaven.examples;

// Import dependencies
import static com.pixaven.OperationConfiguration.settings;
import com.pixaven.Pixaven;
import com.pixaven.Upload;
import com.pixaven.Response;

public class Example {
    public static void main(String[] args) {

        // Pass your Pixaven API Key to the constructor
        Pixaven pix = new Pixaven("your-api-key");

        // Upload an image for processing with `upload()` method
        final Upload upload = pix.upload("path/to/input.jpg");

        // Resize the image to 100 x 75
        upload.resize(
            settings()
                .set("width", 100)
                .set("height", 75)
        );

        // Automatically enhance the image
        upload.auto(
            settings().set("enhance", true)
        );

        // Adjust sharpness parameter
        upload.adjust(
            settings().set("unsharp", 10)
        );

        try {
            // Finalize the chain with .toJSON()
            final Response response = upload.toJSON();

            // You can access the full JSON metadata with `Response#getMetadata()`
            if (response.isSuccessful()) {
                System.out.println(response.getMetadata().getOutput().get("url"));
            } else {
                System.out.println(response.getMessage());
            }
        } catch (IOException io) {
            // handle IOException
        }
    }
}
```

#### Image fetch
If you already have your source visuals publicly available online, we recommend using Image Fetch by default. That way you only have to send a JSON payload containing image URL and processing steps. This method is also much faster than uploading a full binary representation of the image.

```java
package com.pixaven.examples;

// Import dependencies
import static com.pixaven.OperationConfiguration.settings;
import com.pixaven.Pixaven;
import com.pixaven.Fetch;
import com.pixaven.Response;

public class Example {
    public static void main(String[] args) {

        // Pass your Pixaven API Key to the constructor
        Pixaven pix = new Pixaven("your-api-key");

        // Provide a publicly available image URL with `fetch()` method
        final Fetch fetch = pix.fetch("https://www.website.com/image.jpg");

        // Apply Gaussian blur
        fetch.filter(
            settings()
                .set("blur", settings()
                    .set("mode", "gaussian")
                    .set("value", 10)
                )
        );

        // Use PNG as output format
        fetch.output(
            settings().set("format", "png")
        );

        try {
            // Finalize the chain with .toFile()
            final Response response = fetch.toFile("path/to/output.jpg");

            // You can access the full JSON metadata with `Response#getMetadata()`
            if (response.isSuccessful()) {
                System.out.println(response.getMetadata().getOutput().get("url"));
            } else {
                System.out.println(response.getMessage());
            }
        } catch (IOException io) {
            // handle IOException
        }
    }
}
```

### License
This software is distributed under the MIT License. See the [LICENSE](LICENSE) file for more information.

<p align="center"><br><br><a href="https://www.pixaven.com"><img src="media/logo-mono-light.png" alt="Pixaven" width="165" height="42"/></a></p>