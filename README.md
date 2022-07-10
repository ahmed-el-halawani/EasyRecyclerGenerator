# easy_recycler_generator
 Easy and modern way to make recycler view list
 
 ## installation
 
 Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.ahmedgomaa97:EasyRecyclerGenerator:Tag'
	}


## Example


        rvList(
            binding.rvList,
            GridLayoutManager(context, 3),
        ) {
            listBuilder(
                binding = ItemIntentInfoButtonBinding::inflate,
                children = List(1000) { "ahmed $it" }
            ) { v, i ->
                v.tvDescription.text = i
                v.tvName.text = i
            }

            listBuilder(
                binding = ItemTableRowBinding::inflate,
                children = List(1000) { "gomaa $it" }
            ) { v, i ->
                v.tvColumn1.text = i
                v.tvColumn2.text = i
                v.tvColumn3.text = i
                v.tvColumn4.text = i
            }
        }
