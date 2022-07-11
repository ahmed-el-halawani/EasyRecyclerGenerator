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
 
Step 2. Add the dependency  [![](https://jitpack.io/v/ahmedgomaa97/EasyRecyclerGenerator.svg)](https://jitpack.io/#ahmedgomaa97/EasyRecyclerGenerator)

	dependencies {
	        implementation 'com.github.ahmedgomaa97:EasyRecyclerGenerator:1.2'
	}


## Example


     rvList(
            binding.rvList,
            GridLayoutManager(this, 3),
        ) {
            listBuilder(
                binding = ItemIntentInfoButtonBinding::inflate,
                children = vm.list
            ) { v, data ->
                v.tvDescription.text = data
                v.tvName.text = data
                //                v.button.setOnClickListener { removeItem(data) }
                v.button.setOnClickListener {
                    addNewItem(data)
                }
            }

        }
	
	private fun RvListFactory.addNewItem(data: String) {
		vm.list.add(data)
		addItem(
		    binding = ItemIntentInfoButtonBinding::inflate,
		    child = data + "new"
		) { binding, innerData ->
		    binding.tvDescription.text = innerData
		    binding.tvName.text = innerData
		    binding.button.setOnClickListener {
			addNewItem(innerData)
		    }
		}
	    }
